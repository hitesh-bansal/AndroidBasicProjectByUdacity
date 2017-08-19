package com.example.android.inventoryapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.inventoryapp.Data.ProductContract.ProductEntry;

public class ProductDataBaseHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = ProductDataBaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;
    public ProductDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {
            String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " +ProductEntry.TABLE_NAME + " ("
                    + ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0, "
                    + ProductEntry.product_image+ " TEXT, "
                    + ProductEntry.product_name+ " TEXT NOT NULL, "
                    + ProductEntry.product_quantity + " INTEGER, "
                    + ProductEntry.product_price + " INTEGER NOT NULL DEFAULT 0);";
            db.execSQL(SQL_CREATE_PETS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
