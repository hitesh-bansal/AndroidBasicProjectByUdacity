package com.example.android.tourguideapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView historic = (ImageView) findViewById(R.id.historic);
        historic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historicIntent = new Intent(MainActivity.this, Historics.class);
                startActivity(historicIntent);
            }
        });
        ImageView restaurent = (ImageView) findViewById(R.id.restaurent);
        restaurent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restuarentIntent = new Intent(MainActivity.this, Restaurents.class);
                startActivity(restuarentIntent);
            }
        });
        ImageView hotels = (ImageView) findViewById(R.id.hotel);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hotelsIntent = new Intent(MainActivity.this, Hotel.class);
                startActivity(hotelsIntent);
            }
        });
        ImageView Parks = (ImageView) findViewById(R.id.parks);
        Parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ParksIntent = new Intent(MainActivity.this, Park.class);
                startActivity(ParksIntent);
            }

        });
    }
}