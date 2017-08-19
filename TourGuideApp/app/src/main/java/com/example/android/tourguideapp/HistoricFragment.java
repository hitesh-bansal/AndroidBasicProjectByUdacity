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

public class HistoricFragment extends Fragment {
    public HistoricFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.tour, container, false);
        ArrayList<tour> tours = new ArrayList<>();
        tours.add(new tour(R.drawable.indiagate, R.string.india_gate, R.string.rajpath_indiagate));
        tours.add(new tour(R.drawable.qutubminar, R.string.Qutub_Minar, R.string.maharauli_delhi));
        tours.add(new tour(R.drawable.akshardham, R.string.Akshar_Dham, R.string.Noida_delhi));
        tours.add(new tour(R.drawable.lotus,R.string.Lotus_Temple, R.string.lotus_delhi));
        tours.add(new tour(R.drawable.birla,R.string.Birla_Temple , R.string.Mandir_delhi));
        Touradpter adpter = new Touradpter(getActivity(), tours);
        ListView listview = (ListView) rootView.findViewById(R.id.list);

        listview.setAdapter(adpter);

        return rootView;
    }

};
