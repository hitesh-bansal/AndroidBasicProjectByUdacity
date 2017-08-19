package com.example.android.booklist;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context , List<Book> Books)
    {
        super(context,0,Books);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }
        Book currentbook = getItem(position);
        TextView name = (TextView) listItemView.findViewById(R.id.name_of_book);
        String bname = currentbook.getBookName();
        name.setText(bname);

        TextView name1 = (TextView) listItemView.findViewById(R.id.author_name);
        String aname = currentbook.getAutherName();
        name1.setText(aname);
        return listItemView;
    }
}

