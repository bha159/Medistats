package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class UserFamilyHist extends AppCompatActivity
{
    TextView par_hist_tx, sib_hist_tx;
    ImageView par_hist_im, sib_hist_im;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_familyhist);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ImageView backbtn = (ImageView) findViewById(R.id.fhist_backbtn);
        par_hist_tx = (TextView) findViewById(R.id.parent_hist_tx);
        sib_hist_tx = (TextView) findViewById(R.id.sibling_hist_tx);
        par_hist_im = (ImageView) findViewById(R.id.parent_hist_im);
        sib_hist_im = (ImageView) findViewById(R.id.sibling_hist_im);

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserFamilyHist.this,UserHistory.class);
                startActivity(intent);
                finish();
            }
        });
        //TextView
        par_hist_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserFamilyHist.this,UserParentHist.class);
                startActivity(intent);
                finish();
            }
        });
        sib_hist_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserFamilyHist.this,UserSibHist.class);
                startActivity(intent);
                finish();
            }
        });
        //ImageView
        par_hist_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserFamilyHist.this,UserParentHist.class);
                startActivity(intent);
                finish();
            }
        });
        sib_hist_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserFamilyHist.this,UserSibHist.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserFamilyHist.this,UserHistory.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

