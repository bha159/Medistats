package com.example.android.medistats;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity
{
    private int mYear,mMonth,mDay;

    TextView user_login;
    TextView doc_login;
    ImageButton user,doc,user_dob,doc_dob;
    Button upload;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final LinearLayout doc_up = (LinearLayout) findViewById(R.id.doc_up);
        final LinearLayout user_up = (LinearLayout) findViewById(R.id.user_up);
        user = (ImageButton) findViewById(R.id.user);
        doc = (ImageButton) findViewById(R.id.doc);
        final TextView tx = (TextView)findViewById(R.id.user_dob_tx);
        final TextView tx1 = (TextView)findViewById(R.id.doc_dob_tx);
        user_dob = (ImageButton) findViewById(R.id.user_dob);
        doc_dob = (ImageButton) findViewById(R.id.doc_dob);
        user_login = (TextView) findViewById(R.id.user_login);
        doc_login = (TextView) findViewById(R.id.doc_login);
        upload = (Button) findViewById(R.id.doc_proof);

        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                user_up.setVisibility(View.VISIBLE);
                doc_up.setVisibility(View.GONE);
            }
        });

        doc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                user_up.setVisibility(View.GONE);
                doc_up.setVisibility(View.VISIBLE);
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

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tx.setText(sdf.format(myCalendar.getTime()));
                tx1.setText(sdf.format(myCalendar.getTime()));
            }
        };

        user_dob.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(SignUp.this,new DatePickerDialog.OnDateSetListener()
                {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                tx.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        doc_dob.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(SignUp.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear,mMonth,mDay);

                        tx1.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}