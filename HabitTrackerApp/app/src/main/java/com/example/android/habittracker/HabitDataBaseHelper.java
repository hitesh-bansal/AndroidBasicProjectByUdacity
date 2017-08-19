package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android.habittracker.Contract.habit;

public class HabitDataBaseHelper extends SQLiteOpenHelper{
    public static final String log_Tag = HabitDataBaseHelper.class.getSimpleName();
    private static final  String DataBase_Name = "habits.db";
    private static final int DataBase_ver = 1;
    public HabitDataBaseHelper(Context context) {

        super(context ,DataBase_Name,null,DataBase_ver);
    }
    @Override
    public void onCreate(SQLiteDatabase database)
    {

        String Create_Table = "Create table "+ habit.TABLE_NAME+" ("
                +habit._ID+" INTEGER  PRIMARY KEY AUTOINCREMENT,"
                +habit.Book_Reading+" TEXT ,"
                +habit.ANY_SPORTS_PLAYING+" TEXT,"
                +habit.ANY_MEDICINE+" TEXT);";
        database.execSQL(Create_Table);
        Log.i("in DAta base","oncreate");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int old_ver,int new_ver)
    {

    }


}
