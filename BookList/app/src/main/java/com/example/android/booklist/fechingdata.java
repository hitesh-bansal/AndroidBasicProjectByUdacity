package com.example.android.booklist;

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

public class fechingdata {
    private fechingdata() {

    }
    private static final String LOG_TAG = fechingdata.class.getSimpleName();
    public static List<Book> fetchData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try
        {
            jsonResponse = makeHttp(url);
        }
        catch(IOException e)
        {
        }
        List<Book> Book = extractDataFrom(jsonResponse);
        return Book;
    }
    private static URL createUrl(String url)
    {
        URL finalurl=null;
        try
        {
            finalurl = new URL(url);
        }
        catch (MalformedURLException e)
        {

        }
        return finalurl;
    }
    private static String makeHttp(URL url) throws IOException
    {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = getStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code-> " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in retrieving data ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String getStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
        }
        return builder.toString();
    }
    private static List<Book> extractDataFrom(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        List<Book> finallist = new ArrayList<>();
        try {
            JSONObject Response = new JSONObject(s);
            JSONArray array = Response.getJSONArray("items");
            for(int i=0;i<array.length();i++) {
                JSONObject feature = array.optJSONObject(i);
                JSONObject book_volume_Info = feature.optJSONObject("volumeInfo");
                String name_of_book = book_volume_Info.optString("title");
                String name_of_author = book_volume_Info.optString("authors");
                finallist.add(new Book(name_of_author,name_of_book));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "problem with feching the data " , e);
        }
        return finallist;
    }

}

