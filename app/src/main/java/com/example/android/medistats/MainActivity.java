package com.example.android.medistats;

//import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import static com.example.android.medistats.R.id.userin;

public class MainActivity extends AppCompatActivity
{
    Button signup;
    Button data;
    Button user;
    Button doc;
    Button ivf;
    Button datain;
    Button userin;
    Button docin;
    Button ivfin;
    Button signin;
    TextView forgetpas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (Button) findViewById(R.id.signup);
        data = (Button) findViewById(R.id.data);
        user = (Button)findViewById(R.id.userup);
        doc = (Button)findViewById(R.id.docup);
        ivf = (Button) findViewById(R.id.ivf);
        signin = (Button) findViewById(R.id.signin);
        datain = (Button) findViewById(R.id.datain);
        userin = (Button)findViewById(R.id.userin);
        docin = (Button)findViewById(R.id.docin);
        ivfin = (Button) findViewById(R.id.ivfin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setVisibility(View.VISIBLE);
                ivf.setVisibility(View.VISIBLE);
                datain.setVisibility(View.INVISIBLE);
                ivfin.setVisibility(View.INVISIBLE);
                userin.setVisibility(View.INVISIBLE);
                docin.setVisibility(View.INVISIBLE);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datain.setVisibility(View.VISIBLE);
                ivfin.setVisibility(View.VISIBLE);
                data.setVisibility((View.INVISIBLE));
                ivf.setVisibility(View.INVISIBLE);
                user.setVisibility(View.INVISIBLE);
                doc.setVisibility(View.INVISIBLE);
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                doc.setVisibility(View.VISIBLE);
                ivf.setVisibility(View.INVISIBLE);
            }
        });

        datain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userin.setVisibility(View.VISIBLE);
                docin.setVisibility(View.VISIBLE);
                ivfin.setVisibility(View.INVISIBLE);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivf.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this,SignUpUser.class);
                startActivity(intent);
                finish();
            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivf.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this,SignUpDoc.class);
                startActivity(intent);
                finish();
            }
        });

        userin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivfin.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this,SignInUser.class);
                startActivity(intent);
                finish();
            }
        });

        docin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivfin.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this,SignInUser.class);
                startActivity(intent);
                finish();
            }
        });

        forgetpas = (TextView) findViewById(R.id.forgetpass);
        forgetpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPass.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}

