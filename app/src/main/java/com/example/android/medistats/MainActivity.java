package com.example.android.medistats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Button database;
    Button ivf;
    TextView user_name;
    TextView password;
    TextView forgetpass;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        database = (Button) findViewById(R.id.database);
        ivf = (Button) findViewById(R.id.ivf);
        user_name = (TextView) findViewById(R.id.user_name);
        password = (TextView) findViewById(R.id.password);
        forgetpass = (TextView) findViewById(R.id.forgetpass);
        signup = (TextView) findViewById(R.id.signup);

        database.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,DataBase.class);
                startActivity(intent);
                finish();
            }
        });

        ivf.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                Intent intent = new Intent(MainActivity.this,IVF.class);
                startActivity(intent);
                finish();
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                Intent intent = new Intent(MainActivity.this,ForgetPass.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
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