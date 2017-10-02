package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class UserHistoryDetail extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_hist_detail);
        ImageView backbtn = (ImageView) findViewById(R.id.uhist_detail_backbtn);
        Spinner med = (Spinner) findViewById(R.id.uhist_detail_dis);

        //Adapter Created
        ArrayAdapter<CharSequence> med_ad = ArrayAdapter.createFromResource(this,
                R.array.diseases, R.layout.cutom_spinner);
        //Aadapter applied
        med.setAdapter(med_ad);

        backbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UserHistoryDetail.this,UserHistory.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(UserHistoryDetail.this,UserHistory.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}