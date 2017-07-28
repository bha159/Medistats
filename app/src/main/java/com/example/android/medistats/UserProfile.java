package com.example.android.medistats;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

public class UserProfile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_prof);
        RelativeLayout view = (RelativeLayout)findViewById(R.id.rel);

        view.setOutlineProvider(new ViewOutlineProvider()
        {
            @Override
            public void getOutline(View view, Outline outline)
            {
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
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
