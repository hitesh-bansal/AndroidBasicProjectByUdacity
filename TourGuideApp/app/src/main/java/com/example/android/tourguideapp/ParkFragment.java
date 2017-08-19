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

public class ParkFragment extends Fragment {

    public ParkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tour, container, false);

        final ArrayList<tour> tours = new ArrayList<tour>();
        tours.add(new tour(R.drawable.park1, R.string.budda_jayanti, R.string.vandemataram_Marg));
        tours.add(new tour(R.drawable.park2, R.string.the_Lodi_Garden,R.string.lodi_garden));
        tours.add(new tour(R.drawable.park3, R.string.japanese_Park, R.string.Swarn_Jayanti));
        tours.add(new tour(R.drawable.park4, R.string.national_rose,R.string.satya_marg));
        tours.add(new tour(R.drawable.park5, R.string.deer_Park,R.string.new_delhi));

        ListView listview = (ListView) rootView.findViewById(R.id.list);
        Touradpter adpter = new Touradpter(getActivity(), tours);
        listview.setAdapter(adpter);
        return rootView;
    }

}
