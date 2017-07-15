package com.example.android.medistats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ratcoder on 23/6/17.
 */

public class Splash extends AppCompatActivity
{
    Bitmap logo = findViewById(R.id.logo);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        //writeOnDrawable(logo,"MEDISTATS");
        startActivity(intent);
        logo.setBackgroundResource(R.drawable.splash_animation);
        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) logo.getBackground();
        // Start the animation (looped playback by default).
        frameAnimation.start();
        finish();
    }
}
