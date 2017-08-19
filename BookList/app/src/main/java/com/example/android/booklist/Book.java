package com.example.android.booklist;


public class Book {
    private String bName;
    private String aName;
    public Book(String b, String a )
    {
        bName = b;
        aName = a;
    }
    public String getBookName()
    {
        return bName;
    }
    public String getAutherName()
    {
        return aName;
    }

}
