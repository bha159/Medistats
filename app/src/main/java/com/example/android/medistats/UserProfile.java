package com.example.android.medistats;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserProfile extends AppCompatActivity {
    ImageButton cal;
    TextView dob, upload;
    Spinner hei_f, hei_i, pre_mc, gen_mc;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_prof);
        ImageView backbtn = (ImageView) findViewById(R.id.prof_backbtn);
        ImageButton edit_dp = (ImageButton) findViewById(R.id.edit_dp);
        cal = (ImageButton) findViewById(R.id.edit_dob);
        dob = (TextView) findViewById(R.id.edit_dob_tx);
        upload = (TextView) findViewById(R.id.textView);
        hei_f = (Spinner) findViewById(R.id.edit_heightf);
        hei_i = (Spinner) findViewById(R.id.edit_heighti);
        pre_mc = (Spinner) findViewById(R.id.edit_precond);
        gen_mc = (Spinner) findViewById(R.id.edit_gencond);
        //Spinner added

        //Adapter Created
        ArrayAdapter<CharSequence> heif = ArrayAdapter.createFromResource(this,
                R.array.heightf, R.layout.cutom_spinner);
        //Aadapter applied
        hei_f.setAdapter(heif);
        //Adapater Created
        ArrayAdapter<CharSequence> heii = ArrayAdapter.createFromResource(this,
                R.array.heighti, R.layout.cutom_spinner);
        //Adapter applied
        hei_i.setAdapter(heii);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dob.setText(sdf.format(myCalendar.getTime()));
            }
        };

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(UserProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                        dob.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(UserProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                        dob.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, UserHome.class);
                startActivity(intent);
                finish();
            }
        });

        edit_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, CameraPreview.class);
                startActivity(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, CameraPreview.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserProfile.this, UserHome.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
