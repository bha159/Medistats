package com.example.android.medistats;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class UserProfile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_prof);
        ImageView backbtn = (ImageView) findViewById(R.id.prof_backbtn);
        ImageButton edit_dp = (ImageButton)findViewById(R.id.edit_dp);

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserProfile.this,UserHome.class);
                startActivity(intent);
            }
        });

        edit_dp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserProfile.this,CameraPreview.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserProfile.this,UserHome.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
