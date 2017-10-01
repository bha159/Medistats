package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserHistory extends AppCompatActivity
{
    TextView fam_hist_tx, rec_hist_tx, back_hist_tx;
    ImageView fam_hist_im, rec_hist_im, back_hist_im;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_history);
        ImageView backbtn = (ImageView) findViewById(R.id.hist_backbtn);
        fam_hist_tx = (TextView) findViewById(R.id.family_hist_tx);
        rec_hist_tx = (TextView) findViewById(R.id.recent_hist_tx);
        back_hist_tx = (TextView) findViewById(R.id.back_hist_tx);
        fam_hist_im = (ImageView) findViewById(R.id.family_hist_im);
        rec_hist_im = (ImageView) findViewById(R.id.recent_hist_im);
        back_hist_im = (ImageView) findViewById(R.id.back_hist_im);

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        //TextView
        rec_hist_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        back_hist_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        fam_hist_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserFamilyHist.class);
                startActivity(intent);
                finish();
            }
        });
        //ImageView
        rec_hist_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        back_hist_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        fam_hist_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistory.this,UserFamilyHist.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserHistory.this,UserHome.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
