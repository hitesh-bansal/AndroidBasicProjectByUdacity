package com.example.android.tourguideapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Touradpter extends ArrayAdapter<tour> {

    public Touradpter(Context context, ArrayList<tour> tours) {
        super(context, 0, tours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.tour_list, parent, false);
        }
        tour current = getItem(position);
        ImageView image = (ImageView) listView.findViewById(R.id.image);
        image.setImageResource(current.getImage());
        TextView name = (TextView) listView.findViewById(R.id.name);
        name.setText(current.getName());
        TextView loc = (TextView) listView.findViewById(R.id.location);
        loc.setText(current.getlocation());
        return listView;
    }
};
