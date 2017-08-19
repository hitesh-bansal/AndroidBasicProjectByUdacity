package com.example.android.musicplayerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView list = (TextView) findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listActivity.class);
                startActivity(intent);
            }
        });

        TextView albunmbs = (TextView) findViewById(R.id.albumbs);
        albunmbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumbsActivity.class);
                startActivity(intent);
            }
        });

        TextView allsongs = (TextView) findViewById(R.id.all_songs);
        allsongs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllSongsActivity.class);
                startActivity(intent);
            }
        });

        TextView artists = (TextView) findViewById(R.id.artists);
        artists.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(intent);
            }
        });

        ImageView play =(ImageView) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener()
        {
            int no_of_clicks = 0;
            public void onClick(View view) {
                no_of_clicks++;

                if (no_of_clicks % 2 == 0) {
                    ImageView p = (ImageView) findViewById(R.id.play);
                    p.setImageResource(R.drawable.play);

                }
                else
                {
                    ImageView p = (ImageView) findViewById(R.id.play);
                    p.setImageResource(R.drawable.pause);
                }
            }
        });
    }
}
