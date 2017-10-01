package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class UserParentHist extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_parenthist);
        ImageView backbtn = (ImageView) findViewById(R.id.fhist_backbtn);

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserParentHist.this,UserFamilyHist.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserParentHist.this,UserFamilyHist.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
