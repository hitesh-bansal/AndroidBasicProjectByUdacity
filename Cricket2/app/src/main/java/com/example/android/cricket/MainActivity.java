package com.example.android.cricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.android.cricket.R.id.over;
import static com.example.android.cricket.R.id.run;
import static com.example.android.cricket.R.id.run2;
import static com.example.android.cricket.R.id.wicket2;

public class MainActivity extends AppCompatActivity {
    int runsOfTeamA=0;
    int runsOfTeamB=0;
    int wicketOfTeamA=0;
    int wicketOfTeamB=0;
    int oversOfTeamA=0;
    int oversOfTeamB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addSixForTeamA(View view)
    {
        runsOfTeamA=runsOfTeamA+6;
        display_for_runs1(runsOfTeamA);
    }
    public void addFourForTeamA(View view)
    {
        runsOfTeamA=runsOfTeamA+4;
        display_for_runs1(runsOfTeamA);
    }
    public void addOneForTeamA(View view)
    {
        runsOfTeamA=runsOfTeamA+1;
        display_for_runs1(runsOfTeamA);
    }
    public void addTwoForTeamA(View view)
    {
        runsOfTeamA=runsOfTeamA+2;
        display_for_runs1(runsOfTeamA);
    }
    public void addThreeForTeamA(View view)
    {
        runsOfTeamA=runsOfTeamA+3;
        display_for_runs1(runsOfTeamA);
    }
    public void addSixForTeamB(View view)
    {
        runsOfTeamB=runsOfTeamB+6;
        display_for_runs2(runsOfTeamB);
    }
    public void addFourForTeamB(View view)
    {
        runsOfTeamB=runsOfTeamB+4;
        display_for_runs2(runsOfTeamB);
    }
    public void addOneForTeamB(View view)
    {
        runsOfTeamB=runsOfTeamB+1;
        display_for_runs2(runsOfTeamB);
    }
    public void addTwoForTeamB(View view)
    {
        runsOfTeamB=runsOfTeamB+2;
        display_for_runs2(runsOfTeamB);
    }
    public void addThreeForTeamB(View view)
    {
        runsOfTeamB=runsOfTeamB+3;
        display_for_runs2(runsOfTeamB);
    }
    public void addWicketForTeamA(View view)
    {
        wicketOfTeamA=wicketOfTeamA+1;
        display_for_wicket1(wicketOfTeamA);
    }
    public void addWicketForTeamB(View view)
    {
        wicketOfTeamB=wicketOfTeamB+1;
        display_for_wicket2(wicketOfTeamB);
    }
    public void addOverForTeamA(View view)
    {
        oversOfTeamA=oversOfTeamA+1;
        display_for_over(oversOfTeamA);
    }
    public void addOverForTeamB(View view)
    {
        oversOfTeamB=oversOfTeamB+1;
        display_for_over2(oversOfTeamB);
    }
    public void display_for_runs1(int score)
    {
        TextView scoreView = (TextView) findViewById(run);
        scoreView.setText(String.valueOf(score));
    }
    public void display_for_runs2(int score)
    {
        TextView scoreView = (TextView) findViewById(run2);
        scoreView.setText(String.valueOf(score));
    }
    public void display_for_wicket1(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.wicket);
        scoreView.setText(String.valueOf(score));
    }
    public void display_for_wicket2(int score)
    {
        TextView scoreView = (TextView) findViewById(wicket2);
        scoreView.setText(String.valueOf(score));
    }
    public void display_for_over(int score)
    {
        TextView scoreView = (TextView) findViewById(over);
        scoreView.setText(String.valueOf(score));
    }
    public void display_for_over2(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.over2);
        scoreView.setText(String.valueOf(score));
    }
    public void reset(View view)
    {
        runsOfTeamA=0;
        runsOfTeamB=0;
        wicketOfTeamA=0;
        wicketOfTeamB=0;
        oversOfTeamA=0;
        oversOfTeamB=0;
        display_for_wicket1(wicketOfTeamA);
        display_for_wicket2(wicketOfTeamB);
        display_for_over(oversOfTeamA);
        display_for_over2(oversOfTeamB);
        display_for_runs2(runsOfTeamB);
        display_for_runs1(runsOfTeamA);

    }
}
