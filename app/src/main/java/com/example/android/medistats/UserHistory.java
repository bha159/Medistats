package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserHistory extends AppCompatActivity {
    TextView fam_hist_tx, rec_hist_tx, back_hist_tx;
    ImageView fam_hist_im, rec_hist_im, back_hist_im;
    LinearLayout familyhist;
    TextView par_hist_tx, sib_hist_tx;
    ImageView par_hist_im, sib_hist_im;
    int countfamilyhist = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_history);
        ImageView backbtn = (ImageView) findViewById(R.id.hist_backbtn);
        fam_hist_tx = (TextView) findViewById(R.id.family_hist_tx);
        rec_hist_tx = (TextView) findViewById(R.id.recent_hist_tx);
        back_hist_tx = (TextView) findViewById(R.id.back_hist_tx);
        fam_hist_im = (ImageView) findViewById(R.id.family_hist_im);
        rec_hist_im = (ImageView) findViewById(R.id.recent_hist_im);
        back_hist_im = (ImageView) findViewById(R.id.back_hist_im);
        familyhist = (LinearLayout) findViewById(R.id.user_familyhist);
        //LinearLayout IDs
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //ImageView backbtn = (ImageView) findViewById(R.id.fhist_backbtn);
        par_hist_tx = (TextView) findViewById(R.id.parent_hist_tx);
        sib_hist_tx = (TextView) findViewById(R.id.sibling_hist_tx);
        par_hist_im = (ImageView) findViewById(R.id.parent_hist_im);
        sib_hist_im = (ImageView) findViewById(R.id.sibling_hist_im);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        //TextView
        rec_hist_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserHistoryDetail.class);
                startActivity(intent);
                finish();
            }
        });
        back_hist_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserHistoryDetail.class);
                startActivity(intent);
                finish();
            }
        });
        fam_hist_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countfamilyhist++;
                familyhist.setVisibility(View.VISIBLE);
            }
        });
        //ImageView
        rec_hist_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserHistoryDetail.class);
                startActivity(intent);
                finish();
            }
        });
        back_hist_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserHistoryDetail.class);
                startActivity(intent);
                finish();
            }
        });
        fam_hist_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countfamilyhist++;
                if (countfamilyhist % 2 == 0) {
                    familyhist.setVisibility(View.VISIBLE);
                } else {
                    familyhist.setVisibility(View.GONE);
                }
            }
        });
        //LinearLayout Data
        //TextView
        par_hist_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserParentHist.class);
                startActivity(intent);
                finish();
            }
        });
        sib_hist_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserSibHist.class);
                startActivity(intent);
                finish();
            }
        });
        //ImageView
        par_hist_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserParentHist.class);
                startActivity(intent);
                finish();
            }
        });
        sib_hist_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHistory.this, UserSibHist.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserHistory.this, UserHome.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
