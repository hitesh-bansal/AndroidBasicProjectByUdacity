package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.habittracker.Contract.habit;

public class AddHabit extends AppCompatActivity {

    private EditText book_reading;
    private EditText sport_playing;
    private EditText Any_Medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        book_reading = (EditText) findViewById(R.id.book);
        sport_playing = (EditText) findViewById(R.id.sports);
        Any_Medicine = (EditText) findViewById(R.id.medicine);

    }

    private void insertHabit() {
        String book = book_reading.getText().toString().trim();
        String sports = sport_playing.getText().toString().trim();
        String medicine = Any_Medicine.getText().toString().trim();
        HabitDataBaseHelper mDataBaseHelper = new HabitDataBaseHelper(this);
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(habit.Book_Reading, book);
        value.put(habit.ANY_SPORTS_PLAYING, sports);
        value.put(habit.ANY_MEDICINE, medicine);

        long newRow = db.insert(habit.TABLE_NAME, null, value);
        if (newRow == -1) {
            Toast.makeText(this, "Error with saving the habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "habit saved ", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

