package com.example.android.newsfeedapp;


public class News {

    private String NewsDate;
    private String NewsTitle;
    private String NewsURL;
    public News(String Date, String Title, String url)
    {
        NewsDate=Date;
        NewsTitle=Title;
        NewsURL=url;
    }
    public  String  getDate()
    {
        return NewsDate;
    }
    public  String  getTitle()
    {
        return NewsTitle;
    }
    public  String  getURL()
    {
        return NewsURL;
    }
}
