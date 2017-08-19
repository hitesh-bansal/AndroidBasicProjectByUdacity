package com.example.android.tourguideapp;

public class tour {
    private int mImageResource;
    private int mName;
    private int mLocation;
    public tour(int Image,int name,int location)
    {
        mImageResource = Image;
        mName = name;
        mLocation = location;
    }
    public int getImage()
    {
        return mImageResource;
    }
    public int getName()
    {
        return mName;
    }
    public int getlocation()
    {
        return mLocation;
    }

}
