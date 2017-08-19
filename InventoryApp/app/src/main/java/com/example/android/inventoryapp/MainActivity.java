package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.ListView;
import com.example.android.inventoryapp.Data.ProductContract.ProductEntry;

import static com.example.android.inventoryapp.Data.ProductContract.ProductEntry.product_quantity;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int Product_LOADER = 0;
    ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView productList = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        productList.setEmptyView(emptyView);
        mAdapter = new ProductAdapter(this, null);
        productList.setAdapter(mAdapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position,long id) {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            Uri currentUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
            intent.setData(currentUri);
            startActivity(intent);
        }
    });
    getLoaderManager().initLoader(Product_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.product_image,
                ProductEntry.product_name,
                ProductEntry.product_quantity,
                ProductEntry.product_price
        };
                return new CursorLoader(this,
                        ProductEntry.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertProduct();
                return true;
            case R.id.action_delete_all_entries:
                deleteAllProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void insertProduct() {
        ContentValues values = new ContentValues();
        values.put(ProductEntry.product_image,"android.resource://com.example.android.inventoryapp/drawable/biscuit");
        values.put(ProductEntry.product_name, "Biscuit");
        values.put(ProductEntry.product_quantity, "8");
        values.put(ProductEntry.product_price, "12");
        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
    }
    private void deleteAllProduct() {
        int rowsDeleted = getContentResolver().delete(ProductEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public void AddProduct(View view) {
      Intent intent = new Intent(MainActivity.this, EditorActivity.class);
    startActivity(intent);
    }
}

