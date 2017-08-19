package com.example.android.habittracker;

import android.provider.BaseColumns;

public class Contract {

    private Contract(){

    }
    public static final class habit implements BaseColumns{
        public final static String TABLE_NAME="habit_tracker";
        public final static String _ID = BaseColumns._ID;
        public final static String Book_Reading = "Book";
        public final static String ANY_SPORTS_PLAYING ="Sports";
        public final static String ANY_MEDICINE = "medicine";
    }

}

