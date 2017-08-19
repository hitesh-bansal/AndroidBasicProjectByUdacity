package com.example.android.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void search(View view)
    {
        EditText editText =(EditText) findViewById(R.id.edit_text);
        String word_search = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this , BookActivity.class);
        intent.putExtra("word","word_search");
        startActivity(intent);
    }
}
