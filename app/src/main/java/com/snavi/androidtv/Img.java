package com.snavi.androidtv;

import android.graphics.drawable.Drawable;

class Img {

    // fields
    private Drawable m_image;
    private String   m_title;
    private int      m_imageId;


    Img(Drawable image, int imageId, String title)
    {
        m_image   = image;
        m_imageId = imageId;
        m_title   = title;
    }



    Drawable getImage()
    {
        return m_image;
    }



    String getTitle()
    {
        return m_title;
    }



    int getImageId()
    {
        return m_imageId;
    }
}
