package com.example.android.newsfeedapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(Context context, List<News> Newsfeed) {
        super(context, 0, Newsfeed);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News current_News = (News) getItem(position);
        TextView Date = (TextView) listView.findViewById(R.id.publication_Date);
        String date = current_News.getDate();
        Date.setText(date);
        TextView title_name = (TextView) listView.findViewById(R.id.Title_News);
        String title = current_News.getTitle();
        title_name.setText(title);
        return listView;
    }
}