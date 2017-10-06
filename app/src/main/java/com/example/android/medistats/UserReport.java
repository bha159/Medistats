package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserReport extends AppCompatActivity
{
    LinearLayout ecg, mri, scans;
    TextView ecg_tx, mri_tx, scans_tx;
    ImageView ecg_im, mri_im, scans_im;
    int cecg = 1;
    int cmri = 1;
    int cscan = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reports);
        ImageView backbtn = (ImageView) findViewById(R.id.report_backbtn);
        ecg_tx = (TextView) findViewById(R.id.ecg);
        mri_tx = (TextView) findViewById(R.id.mri);
        scans_tx = (TextView) findViewById(R.id.scans);
        ecg_im = (ImageView) findViewById(R.id.ecg_im);
        mri_im = (ImageView) findViewById(R.id.mri_im);
        scans_im = (ImageView) findViewById(R.id.scans_im);
        ecg = (LinearLayout)findViewById(R.id.ecg_view);
        mri = (LinearLayout) findViewById(R.id.mri_view);
        scans = (LinearLayout) findViewById(R.id.scans_view);

        //Textview changing views
        ecg_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cecg++;
                mri.setVisibility(View.GONE);
                scans.setVisibility(View.GONE);
                ecg.setVisibility(View.VISIBLE);
            }
        });

        mri_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cmri++;
                ecg.setVisibility(View.GONE);
                scans.setVisibility(View.GONE);
                mri.setVisibility(View.VISIBLE);
            }
        });

        scans_tx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cscan++;
                mri.setVisibility(View.GONE);
                ecg.setVisibility(View.GONE);
                scans.setVisibility(View.VISIBLE);
            }
        });

        //Images chaning views
        ecg_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cecg++;
                if (cecg % 2 == 0)
                {
                    mri.setVisibility(View.GONE);
                    scans.setVisibility(View.GONE);
                    ecg.setVisibility(View.VISIBLE);
                }
                else
                {
                    mri.setVisibility(View.GONE);
                    scans.setVisibility(View.GONE);
                    ecg.setVisibility(View.GONE);
                }

            }
        });

        mri_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cmri++;
                if (cmri % 2 == 0)
                {
                    ecg.setVisibility(View.GONE);
                    scans.setVisibility(View.GONE);
                    mri.setVisibility(View.VISIBLE);
                }
                else
                {
                    mri.setVisibility(View.GONE);
                    scans.setVisibility(View.GONE);
                    ecg.setVisibility(View.GONE);
                }
            }
        });

        scans_im.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cscan++;
                if (cscan % 2 == 0)
                {
                    mri.setVisibility(View.GONE);
                    ecg.setVisibility(View.GONE);
                    scans.setVisibility(View.VISIBLE);
                }
                else
                {
                    mri.setVisibility(View.GONE);
                    scans.setVisibility(View.GONE);
                    ecg.setVisibility(View.GONE);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserReport.this,UserHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserReport.this,UserHome.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
