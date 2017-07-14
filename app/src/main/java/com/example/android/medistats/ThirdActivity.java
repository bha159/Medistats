package com.example.shubham.newsreader;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.DatePicker;
import java.util.*;
import java.lang.*;
import java.io.*;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ThirdActivity extends AppCompatActivity {
    EditText etFName,gen,etemail,etmm,etyy,etpass,ages,uname,Password,Username,User,Emailname;

    TextView result,y,x;
    static EditText etDD;
    Button insert;
    static int year;
static int month,day;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.main);
        etFName=(EditText)findViewById(R.id.f_name);

        etDD=(EditText)findViewById(R.id.dd);

        etDD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                showcalender(v);
            }

        });
        uname=(EditText)findViewById(R.id.uname);
        gen=(EditText)findViewById(R.id.gender);
        etemail=(EditText)findViewById(R.id.e_id);

        etpass=(EditText)findViewById(R.id.pass);
        ages=(EditText)findViewById(R.id.age);
        insert=(Button)findViewById(R.id.but1);
        insert.setOnClickListener(new View.OnClickListener(){
            InputStream is=null;
            int i=0;
            @Override
            public void onClick(View ar)
            {
                String gname=""+uname.getText().toString();
                String name=""+etFName.getText().toString();
                String gender=""+gen.getText().toString();
                String dd=""+day;
                String mm=""+month;
                String yy=""+year;

                String pass=""+etpass.getText().toString();
                String age=""+ages.getText().toString();
                String email=""+etemail.getText().toString();

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("name",name));
                nameValuePairs.add(new BasicNameValuePair("dd",dd));
                nameValuePairs.add(new BasicNameValuePair("mm",mm));
                nameValuePairs.add(new BasicNameValuePair("yy",yy));
                nameValuePairs.add(new BasicNameValuePair("pass",pass));
                nameValuePairs.add(new BasicNameValuePair("age",age));
                nameValuePairs.add(new BasicNameValuePair("gender",gender));
                nameValuePairs.add(new BasicNameValuePair("email",email));
                nameValuePairs.add(new BasicNameValuePair("uname",gname));
               // nameValuePairs.add(new BasicNameValuePair("sports","0"));



                try
                {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.43.224/signup.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response=httpClient.execute(httpPost);
                    HttpEntity entity=response.getEntity();
                    is=entity.getContent();
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(ar.getContext(),Login.class);
                    ar.getContext().startActivity(intent);
                                   }

                catch(ClientProtocolException e)
                {
                    Log.e("ClientProtocol","Log_tag");
                    e.printStackTrace();
                }

                catch (IOException e)
                {
                    Log.e("Log_tag","IOException");
                     e.printStackTrace();
                }

            }
        });

        etpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etpass.getText().length() < 6 || etpass == null) {
                    etpass.setError("Invalid Length");
                }
            }

        });

        ages.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ages.getText().length() < 0 || ages.getText().length()> 110 || ages == null) {
                    ages.setError("Error");
                }
            }

        });


        etemail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!validateEmail(etemail.getText().toString()) ) {
                    etemail.setError("Invalid");
                }
            }

        });








        Password=(EditText)findViewById(R.id.pass);
        Password.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Username=(EditText)findViewById(R.id.uname);
                x=(TextView)findViewById(R.id.x);
                String uname=""+Username.getText().toString();
                HashMap<String, String> hash= new  HashMap<>();
                hash.put("Shreeya",uname);

                RequestHandler Rh = new RequestHandler();
                String s=Rh.sendPostRequest("http://192.168.43.224/unamecheck.php",hash);
                if(s.equals("Success"))
                {
                  Toast.makeText(getApplicationContext(),"Username Taken",Toast.LENGTH_LONG).show();
                }
               /*   else
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show(); */
            }

        });

    }

    protected boolean validateEmail(String etemail) {

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(etemail);


        return matcher.matches();
        //return false;


    }







public void showcalender(View v)
{

    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(), "datePicker");
}
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
           year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            etDD.setText(day + "/" + (month + 1) + "/" + year);
        }

    }




}

