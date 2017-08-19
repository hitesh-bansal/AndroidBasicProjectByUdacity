package com.example.android.newsfeedapp;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class fechingNews {
    private fechingNews(){}
    private static final String LOG_TAG = fechingNews.class.getSimpleName();
    public static List<News> extractnews(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with the request.", e);
        }
        List<News> newsFeed = extractJson(jsonResponse);
        return newsFeed;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem with the URL ", e);
        }
        return url;
    }

    private static String makeRequest(URL url) throws IOException {
        String Response = "";
        if (url == null) {
            return Response;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 );
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                Response = Stream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with showing the News.", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return Response;
    }

    private static String Stream(InputStream input) throws IOException {
        StringBuilder output = new StringBuilder();
        if (input != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractJson(String newsJson) {
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        List<News> News = new ArrayList<>();
        try {
            JSONObject baseResponse = new JSONObject(newsJson);
            JSONObject Response = baseResponse.getJSONObject("response");
            JSONArray Result = Response.getJSONArray("results");
            for (int i = 0; i < Result.length(); i++) {
                JSONObject CurrentNews = Result.getJSONObject(i);
                String Date = CurrentNews.optString("webPublicationDate");
                String title = CurrentNews.getString("webTitle");
                String url = CurrentNews.getString("webUrl");
                com.example.android.newsfeedapp.News NewsCurrent  = new News(Date,title, url);
                News.add(NewsCurrent);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem with  showing the News ", e);
        }
        return News;
    }

}
