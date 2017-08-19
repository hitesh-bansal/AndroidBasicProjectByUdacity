package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.inventoryapp.Data.ProductContract;

public class ProductAdapter extends CursorAdapter{

    public ProductAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.Product_name);
        final TextView quantity = (TextView) view.findViewById(R.id.Product_quantity);

        TextView price = (TextView) view.findViewById(R.id.Product_price);
        int p_name = cursor.getColumnIndex(ProductContract.ProductEntry.product_name);
        int p_quantity = cursor.getColumnIndex(ProductContract.ProductEntry.product_quantity);
        int p_price = cursor.getColumnIndex(ProductContract.ProductEntry.product_price);
        final String quan = cursor.getString(p_quantity);
        final int id= cursor.getColumnIndex(ProductContract.ProductEntry._ID);
        final String q_id = cursor.getString(id);
        ImageView image = (ImageView) view.findViewById(R.id.cart);
        int imageString = cursor.getColumnIndex(ProductContract.ProductEntry.product_image);
        String imageStr = cursor.getString(imageString);
        ImageView imageView = (ImageView)view.findViewById(R.id.Image);
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(quan);
                if(q==1)
                {
                    Toast.makeText(context,"Cann't be zero",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    q=q-1;
                    ContentValues value = new ContentValues();
                    value.put(ProductContract.ProductEntry.product_quantity,q);
                    Uri uri = ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI, Long.parseLong(q_id));
                    context.getContentResolver().update(uri,value,null,null);
                }
            }
        });
        Uri imageUri = Uri.parse(imageStr);
        String productName = cursor.getString(p_name);
        String productquantity = cursor.getString(p_quantity);
        String productprice = cursor.getString(p_price);
        name.setText(productName);
        quantity.setText(productquantity);
        price.setText(productprice);
        imageView.setImageURI(imageUri);
    }
}

