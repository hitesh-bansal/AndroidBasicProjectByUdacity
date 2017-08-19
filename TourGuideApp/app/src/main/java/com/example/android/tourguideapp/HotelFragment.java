package com.example.android.tourguideapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelFragment extends Fragment {

    public HotelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.tour, container, false);
        final ArrayList<tour> tours = new ArrayList<tour>();
        tours.add(new tour(R.drawable.hotel1,R.string.Roseate_House,R.string.roseate_add));
        tours.add(new tour( R.drawable.hotel2,R.string.the_Leela,R.string.leela_add));
        tours.add(new tour( R.drawable.hotel3,R.string.The_Oberoi,R.string.oberoi_add));
        tours.add(new tour( R.drawable.hotel4,R.string.taj_mahal,R.string.taj_add));
        tours.add(new tour( R.drawable.hotel5,R.string.the_metropolitan,R.string.metropolitian_add));
        tours.add(new tour( R.drawable.hotel6,R.string.Taj_Palace,R.string.tajhotel_add));
        ListView listview = (ListView)rootView. findViewById(R.id.list);
        Touradpter adpter = new Touradpter(getActivity(),tours);
        listview.setAdapter(adpter);
        return rootView;
    }

}
