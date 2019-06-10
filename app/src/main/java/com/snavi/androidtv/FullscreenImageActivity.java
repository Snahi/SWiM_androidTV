package com.snavi.androidtv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenImageActivity extends Activity {


    // CONST //////////////////////////////////////////////////////////////////////////////////////
    public static final String PHOTO_ID_KEY = "photo_id";
    private static final String CANT_FIND_PHOTO_TOAST = "Couldn't find photo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        setImage();
    }



    private void setImage()
    {
        ImageView img   = findViewById(R.id.activity_fullscreen_image_img);
        Intent intent   = getIntent();
        int imgResource = intent.getIntExtra(PHOTO_ID_KEY, -1);

        if (imgResource != -1)
            img.setImageResource(imgResource);
        else
            Toast.makeText(this, CANT_FIND_PHOTO_TOAST, Toast.LENGTH_LONG).show();
    }
}
