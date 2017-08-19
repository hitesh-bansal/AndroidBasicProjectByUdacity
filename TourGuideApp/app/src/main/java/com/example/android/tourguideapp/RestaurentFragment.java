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

public class RestaurentFragment extends Fragment {

    public RestaurentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.tour, container, false);
        final ArrayList<tour> tours = new ArrayList<tour>();
        tours.add(new tour( R.drawable.res1,R.string.playboy_cafe,R.string.add_too));
        tours.add(new tour( R.drawable.res2,R.string.the_Darzi,R.string.CONNAUGHT_PLACE));
        tours.add(new tour( R.drawable.res3,R.string.the_Junction,R.string.HAUZ_KHAS));
        tours.add(new tour( R.drawable.res4,R.string.metro_Dhaba,R.string.Sec_18));
        tours.add(new tour( R.drawable.res5,R.string.mother_India,R.string.CONNAUGHT_NEW));

        ListView listview = (ListView)rootView. findViewById(R.id.list);
        Touradpter adpter = new Touradpter(getActivity(),tours);
        listview.setAdapter(adpter);
        return rootView;
    }


}
