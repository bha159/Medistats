package com.example.android.medistats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SignUp extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TextView user_login;
        TextView doc_login;
        ImageButton user,doc;
        Button upload;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final LinearLayout doc_up = (LinearLayout) findViewById(R.id.doc_up);
        final LinearLayout user_up = (LinearLayout) findViewById(R.id.user_up);
        user = (ImageButton) findViewById(R.id.user);
        doc = (ImageButton) findViewById(R.id.doc);
        user_login = (TextView) findViewById(R.id.user_login);
        doc_login = (TextView) findViewById(R.id.doc_login);
        upload = (Button) findViewById(R.id.doc_proof);

        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                    //Set the button's appearance
                    button.setSelected(!button.isSelected());
                    if (button.isSelected())
                    {
                        user_up.setVisibility(View.VISIBLE);
                        doc_up.setVisibility(View.GONE);
                        //Handle selected state change
                    }
                }

        });

        doc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                //Set the button's appearance
                button.setSelected(!button.isSelected());
                if (button.isSelected())
                {
                    user_up.setVisibility(View.GONE);
                    doc_up.setVisibility(View.VISIBLE);
                    //Handle selected state change
                }
            }

        });

        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        doc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, CameraPreview.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}