package com.example.android.inventoryapp.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.android.inventoryapp.EditorActivity;
import com.example.android.inventoryapp.MainActivity;
import static com.example.android.inventoryapp.Data.ProductDataBaseHelper.LOG_TAG;

public class ProductProvider extends ContentProvider {
    public static final String Log_TAG = ProductProvider.class.getSimpleName();
    private static final int Product = 1;
    private static final int Product_id = 2;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_Product, Product);
        matcher.addURI(ProductContract.CONTENT_AUTHORITY, ProductContract.PATH_Product + "/#", Product_id);
    }
    private ProductDataBaseHelper mHelper;


    @Override
    public boolean onCreate() {
        mHelper = new ProductDataBaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        int match = matcher.match(uri);
        switch (match) {
            case Product:
                cursor = database.query(ProductContract.ProductEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case Product_id:
                selection = ProductContract.ProductEntry._ID + " =?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                cursor = database.query(ProductContract.ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues value) {
        final int match = matcher.match(uri);
        switch (match) {
            case Product:
                return insertProduct(uri, value);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertProduct(Uri uri, ContentValues value) {
        String name = value.getAsString(ProductContract.ProductEntry.product_name);
        if (name == null) {
            throw new IllegalArgumentException("Requires a Product name");
            //Toast.makeText(getContext(),"Please Enter product name",Toast.LENGTH_SHORT).show();
        }
        Integer quantity = value.getAsInteger(ProductContract.ProductEntry.product_quantity);
        if (quantity == 0) {
            throw new IllegalArgumentException("Reqires Product quantity");
            //Toast.makeText(getContext(),"Please Enter product quantity",Toast.LENGTH_SHORT).show();
        }
        Integer price = value.getAsInteger(ProductContract.ProductEntry.product_price);
        if (price <= 0) {
            throw new IllegalArgumentException("Product requires a valid price");
            //Toast.makeText(getContext(),"Please Enter product price",Toast.LENGTH_SHORT).show();
        }

        SQLiteDatabase database = mHelper.getWritableDatabase();
        long id = database.insert(ProductContract.ProductEntry.TABLE_NAME, null, value);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues value, String selection,
                      String[] selectionArgs) {
        final int match = matcher.match(uri);
        switch (match) {
            case Product:
                return updateProduct(uri, value, selection, selectionArgs);
            case Product_id:
                selection = ProductContract.ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, value, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(ProductContract.ProductEntry.product_name)) {
            String name = values.getAsString(ProductContract.ProductEntry.product_name);
            if (name == null) {
                throw new IllegalArgumentException("Requires Product name");
            }
        }
        if (values.containsKey(ProductContract.ProductEntry.product_quantity)) {
            Integer quantity = values.getAsInteger(ProductContract.ProductEntry.product_quantity);
            if (quantity < 0) {
                throw new IllegalArgumentException("Product Quantity requires");
            }
        }
            if (values.containsKey(ProductContract.ProductEntry.product_price)) {
                Integer price = values.getAsInteger(ProductContract.ProductEntry.product_price);
                if (price <= 0) {
                    throw new IllegalArgumentException("Enter valid price");
                }
            }
            if (values.size() == 0) {
                return 0;
            }
            SQLiteDatabase database = mHelper.getWritableDatabase();
            int rowsUpdated = database.update(ProductContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
            if (rowsUpdated != 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return rowsUpdated;
        }
        @Override
        public int delete (Uri uri, String selection, String[]selectionArgs){
            SQLiteDatabase database = mHelper.getWritableDatabase();
            int rowsDeleted;
            final int match = matcher.match(uri);
            switch (match) {
                case Product:
                    rowsDeleted = database.delete(ProductContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                    break;
                case Product_id:
                    selection = ProductContract.ProductEntry._ID + "=?";
                    selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                    rowsDeleted = database.delete(ProductContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Deletion is not supported for " + uri);
            }
            if (rowsDeleted != 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return rowsDeleted;
        }
        @Override
        public String getType (Uri uri){
            final int match = matcher.match(uri);
            switch (match) {
                case Product:
                    return ProductContract.ProductEntry.CONTENT_LIST_TYPE;
                case Product_id:
                    return ProductContract.ProductEntry.CONTENT_ITEM_TYPE;
                default:
                    throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
            }
        }

    }