package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int Right_answer=0,flag=0;
    CheckBox op8_1;
    CheckBox op8_2;
    CheckBox op8_3;
    CheckBox op8_4;
    boolean o1;
    boolean o2;
    boolean o3;
    boolean o4;
    public void que1_2(View v)
    {
        RadioButton op1_2=(RadioButton) findViewById(R.id.q1_2);
        if (op1_2.isChecked())
            {
                Right_answer++;
            }
    }
    public void que2_2(View v)
    {
        RadioButton op2_2=(RadioButton) findViewById(R.id.q2_2);
        if (op2_2.isChecked())
        {
            Right_answer++;
        }
    }
    public void que3_1(View v)
    {
        RadioButton op3_1=(RadioButton) findViewById(R.id.q3_1);
        if (op3_1.isChecked())
        {
            Right_answer++;
        }
    }
    public void que4_3(View v)
    {
        RadioButton op4_3=(RadioButton) findViewById(R.id.q4_3);
        if (op4_3.isChecked())
        {
            Right_answer++;
        }
    }
    public void que5_1(View v)
    {
        RadioButton op5_1=(RadioButton) findViewById(R.id.q5_1);
        if (op5_1.isChecked())
        {
            Right_answer++;
        }
    }
       public void que6_4(View v)
    {
        RadioButton op6_4=(RadioButton) findViewById(R.id.q6_4);
        if (op6_4.isChecked())
        {
            Right_answer++;
        }
    }

    public void que7_1(View v)
    {
        RadioButton op7_1=(RadioButton) findViewById(R.id.q7_1);
        if (op7_1.isChecked()) {
            Right_answer++;
        }
    }
    public void que8_1(View v)
    {
         op8_1 = (CheckBox) findViewById(R.id.q8_1);
        o1=op8_1.isChecked();
    }
    public void que8_2(View v)
    {
       CheckBox op8_2 = (CheckBox) findViewById(R.id.q8_2);
        o2=op8_2.isChecked();
    }
    public void que8_3(View v)
    {
       CheckBox op8_3 = (CheckBox) findViewById(R.id.q8_3);
        o3=op8_3.isChecked();
    }
    public void que8_4(View v)
    {
        CheckBox op8_4 = (CheckBox) findViewById(R.id.q8_4);
        o4=op8_4.isChecked();
    }
    public void onsubmit(View view) {
        if(!o1 && o2 && o3 && !o4 )
        {
            Right_answer++;
        }
        EditText text = (EditText) findViewById(R.id.name);
        String name = text.getText().toString();
        EditText q9 = (EditText) findViewById(R.id.q9);
        String name_president = q9.getText().toString();
        if ((name_president.equals("Pranab Mukherjee")))
            Right_answer++;
        Toast.makeText(this, name + ",You give " + Right_answer + " correct Answer!!!", Toast.LENGTH_SHORT).show();
        if (Right_answer > 6) {
            Toast.makeText(this, name + ", You are awesome ", Toast.LENGTH_SHORT).show();
        } else if (Right_answer < 6 && Right_answer > 4) {
            Toast.makeText(this, name + ", You are good ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, name + ", please try again!!! ", Toast.LENGTH_SHORT).show();
        }
    }
}



