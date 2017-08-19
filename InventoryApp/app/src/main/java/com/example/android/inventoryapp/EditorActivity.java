package com.example.android.inventoryapp;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.inventoryapp.Data.ProductContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int Current_product_loader = 0;
    private Uri current_Product_uri;
    private EditText product_name_text;
    private TextView product_quantity_text;
    private EditText product_price_text;
    private ImageView product_image_text;
    String product_image = null;
    private static final int SELECT_PICTURE = 100;
    private boolean productChanged = false;
    Uri i_Uri;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        button = (Button) findViewById(R.id.order);
        Intent intent = getIntent();
        current_Product_uri = intent.getData();
        if (current_Product_uri == null) {
            setTitle("Add New Product ");
            invalidateOptionsMenu();
            button.setVisibility(View.INVISIBLE);
        } else {
            setTitle("EditCurrentProduct");
            button.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(Current_product_loader, null, this);
        }

        product_name_text = (EditText) findViewById(R.id.P_name);
        product_quantity_text = (TextView)findViewById(R.id.p_quantity);
        product_price_text = (EditText) findViewById(R.id.p_price);
        product_image_text = (ImageView) findViewById(R.id.p_image);

        Button Plus = (Button) findViewById(R.id.plus);
        Plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(product_quantity_text.getText().toString());
                if(q==10)
                {
                    Toast.makeText(getApplicationContext(),"Product not greater the 10 ",Toast.LENGTH_SHORT);
                }
                else
                {
                    q++;
                }
                product_quantity_text.setText(String.valueOf(q));
            }
        });
        Button Minus = (Button) findViewById(R.id.minus);
        Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(product_quantity_text.getText().toString());
                if(q==0)
                {
                    Toast.makeText(getApplicationContext(),"Already Zero",Toast.LENGTH_SHORT);
                }
                else
                {
                    q--;
                }
                product_quantity_text.setText(String.valueOf(q));
            }
        });
        Button Photo = (Button) findViewById(R.id.photo);
        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PICTURE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email_intent = new Intent(android.content.Intent.ACTION_SEND);
                email_intent.setType("plain/text");
                email_intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {"customerName@gmail.com" });
                email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Product Order ");
                email_intent.putExtra(android.content.Intent.EXTRA_TEXT," Product Name "+product_name_text.getText()+"\nProduct Quantity "+product_quantity_text.getText());
                startActivity(Intent.createChooser(email_intent, "Send mail..."));
            }
        });
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            productChanged = true;
            return false;
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
           if (requestCode == SELECT_PICTURE) {
                i_Uri = data.getData();
                product_image = i_Uri.toString();
                product_image_text.setImageURI(Uri.parse(product_image));
           }
        }
   }

    private void saveProduct() {
        String productImage= "";
        if(i_Uri!= null)
        {
            productImage=i_Uri.toString();
        }
        String productName = product_name_text.getText().toString().trim();
        String productQuantity = product_quantity_text.getText().toString().trim();
        String productPrice = product_price_text.getText().toString().trim();
        if (TextUtils.isEmpty(productName) ||
                TextUtils.isEmpty(productQuantity) || TextUtils.isEmpty(productPrice)) {
            Toast.makeText(getApplicationContext(),"All Fields are Necessary",Toast.LENGTH_LONG).show();
            return;
        }

        if (current_Product_uri == null && TextUtils.isEmpty(productName) &&
                TextUtils.isEmpty(productQuantity) && TextUtils.isEmpty(productPrice)) {
            Toast.makeText(getApplicationContext(),"All Fields are Necessary OtherWise Product is not Saved in the DataBase ",Toast.LENGTH_LONG).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.product_name, productName);
        values.put(ProductContract.ProductEntry.product_quantity, productQuantity);
        values.put(ProductContract.ProductEntry.product_price, productPrice);
        values.put(ProductContract.ProductEntry.product_image,productImage);
        if (current_Product_uri == null) {
            Uri newUri = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Error with saving the Product ",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Product saved ",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(current_Product_uri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, "Error with updating Product",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update Saved ",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (current_Product_uri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                finish();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if (!productChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!productChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.product_image,
                ProductContract.ProductEntry.product_name,
                ProductContract.ProductEntry.product_quantity,
                ProductContract.ProductEntry.product_price,
        };
        return new android.content.CursorLoader(this,
                current_Product_uri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null || data.getCount() < 1) {
            return;
        }
        if (data.moveToFirst()) {
            int image = data.getColumnIndex(ProductContract.ProductEntry.product_image);
            int name = data.getColumnIndex(ProductContract.ProductEntry.product_name);
            int quantity = data.getColumnIndex(ProductContract.ProductEntry.product_quantity);
            int price = data.getColumnIndex(ProductContract.ProductEntry.product_price);

            String pimage = data.getString(image);
            String pname = data.getString(name);
            int pquantity = data.getInt(quantity);
            int pprice = data.getInt(price);
            product_image_text.setImageURI(Uri.parse(pimage));
            product_image_text.setImageURI(Uri.parse(pimage));
            product_name_text.setText(pname);
            product_quantity_text.setText(Integer.toString(pquantity));
            product_price_text.setText(Integer.toString(pprice));

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        product_name_text.setText("");
        product_quantity_text.setText("");
        product_price_text.setText("");
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(" Discard your changes and quit editing?");
        builder.setPositiveButton("Discard your changes and quit editing?", discardButtonClickListener);
        builder.setNegativeButton("keep_editing", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You sure to delete the product ");
        builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        if (current_Product_uri != null) {
            int rowsDeleted = getContentResolver().delete(current_Product_uri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Error with deleting the product ",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, " product deleted",
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}

