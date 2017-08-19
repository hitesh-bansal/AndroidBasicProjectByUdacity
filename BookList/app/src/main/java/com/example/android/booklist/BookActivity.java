package com.example.android.booklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static String Book_URL =
            "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";
    private TextView emptyView;
    private static final int book_loader =1;
    private BookAdapter mAdaapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ListView bookView = (ListView) findViewById(R.id.list);
        emptyView= (TextView) findViewById(R.id.empty_view);
        bookView.setEmptyView(emptyView);
        mAdaapter = new BookAdapter(this,new ArrayList<Book>());
        bookView.setAdapter(mAdaapter);
        LoaderManager loaderManager= null;
        Intent intent = getIntent();
        String word_search = intent.getStringExtra("word");
        Book_URL= "https://www.googleapis.com/books/v1/volumes?q="+word_search+"&maxResults=30";
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(book_loader, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet_connection);
        }
    }
    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, Book_URL);
    }
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        emptyView.setText(R.string.no_books);
        mAdaapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdaapter.addAll(books);
        }
        else
        {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdaapter.clear();
    }


}

