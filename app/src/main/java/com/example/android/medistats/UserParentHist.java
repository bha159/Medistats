package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class UserParentHist extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_parenthist);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ImageView backbtn = (ImageView) findViewById(R.id.par_hist_backbtn);
        Spinner gene = (Spinner) findViewById(R.id.par_gen);

        //Adapter Created
        ArrayAdapter<CharSequence> gene_ad = ArrayAdapter.createFromResource(this,
                R.array.genetic_disease, R.layout.cutom_spinner);
        //Aadapter applied
        gene.setAdapter(gene_ad);

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
