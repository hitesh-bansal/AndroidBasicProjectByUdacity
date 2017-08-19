package com.example.android.habittracker;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.android.habittracker.Contract.habit;

public class MainActivity extends AppCompatActivity {

    private HabitDataBaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e( "in main ","start " );
        TextView add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddHabit.class);
                startActivity(intent);
            }
        });
        mHelper = new HabitDataBaseHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("in","onstart");
        showData();
    }
    private void showData() {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        String[] projection = {
                habit._ID,
                habit.Book_Reading,
                habit.ANY_SPORTS_PLAYING,
                habit.ANY_MEDICINE};
       Cursor cursor = database.query(habit.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView display = (TextView) findViewById(R.id.showhabit);
        try {
            display.setText("The habit_tracker table contains  " + cursor.getCount() + "habits .\n");
            display.append(habit._ID + "-" +
                    habit.Book_Reading + "-" +
                    habit.ANY_SPORTS_PLAYING + "-" +
                    habit.ANY_MEDICINE + "\n");
            int column_id = cursor.getColumnIndex(habit._ID);
            int book = cursor.getColumnIndex(habit.Book_Reading);
            int sports = cursor.getColumnIndex(habit.ANY_SPORTS_PLAYING);
            int medicine = cursor.getColumnIndex(habit.ANY_MEDICINE);
            while (cursor.moveToNext()) {
                int cid = cursor.getInt(column_id);
                String cwtime = cursor.getString(book);
                String cSports = cursor.getString(sports);
                String cmedicine = cursor.getString(medicine);
                display.append(cid + "-" +
                        cwtime + "-" +
                        cSports + "-" +
                        cmedicine + "\n\n");
            }

        } finally {
            cursor.close();
        }
    }
    private void insertHabit()
    {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(habit.Book_Reading,"Romantic");
        value.put(habit.ANY_SPORTS_PLAYING,"Cricket");
        value.put(habit.ANY_MEDICINE,"Yes");
        long newRow = db.insert(habit.TABLE_NAME,null,value);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        getMenuInflater().inflate(R.menu.catalog,m);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.dummy_data:
                insertHabit();
                showData();
                return true;
            case R.id.delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
