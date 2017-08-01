package com.example.android.medistats;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    //Doctor sign up page
    EditText doc_name;
    EditText hospital_name;
    EditText doc_email;
    EditText doc_phone_no;
    EditText doc_username;
    EditText doc_password;
    EditText doc_cnfrm_password;
    Button doc_signup;
    //User sign up page
    EditText user_namef;
    EditText user_namel;
    EditText user_email;
    EditText user_phone_no;
    EditText user_username;
    EditText user_password;
    EditText user_cnfrm_password;
    Button user_signup, upload;
    int selectedId;
    private int mYear, mMonth, mDay;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    TextView user_login;
    TextView doc_login;
    ImageButton user;
    ImageButton doc;
    ImageButton user_dob, doc_dob;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final LinearLayout doc_up = (LinearLayout) findViewById(R.id.doc_up);
        final LinearLayout user_up = (LinearLayout) findViewById(R.id.user_up);
        final TextView tx = (TextView) findViewById(R.id.user_dob_tx);
        final TextView tx1 = (TextView) findViewById(R.id.doc_dob_tx);
        user_dob = (ImageButton) findViewById(R.id.user_dob);
        doc_dob = (ImageButton) findViewById(R.id.doc_dob);
        user = (ImageButton) findViewById(R.id.user);
        doc = (ImageButton) findViewById(R.id.doc);
        user_login = (TextView) findViewById(R.id.user_login);
        doc_login = (TextView) findViewById(R.id.doc_login);
        upload = (Button) findViewById(R.id.doc_proof);
        doc_signup = (Button) findViewById(R.id.doc_signup);
        user_signup = (Button) findViewById(R.id.user_signup);

        //DOCTOR
        doc_name = (EditText) findViewById(R.id.doc_name);
        hospital_name = (EditText) findViewById(R.id.hospital_name);
        doc_email = (EditText) findViewById(R.id.doc_email);
        doc_phone_no = (EditText) findViewById(R.id.doc_phone_no);
        doc_username = (EditText) findViewById(R.id.doc_username);
        doc_password = (EditText) findViewById(R.id.doc_password);
        doc_cnfrm_password = (EditText) findViewById(R.id.doc_cnfrm_password);

        //USER
        user_namef = (EditText) findViewById(R.id.user_namef);
        user_namel = (EditText) findViewById(R.id.user_namel);
        user_email = (EditText) findViewById(R.id.user_email);
        user_phone_no = (EditText) findViewById(R.id.user_phone_no);
        user_username = (EditText) findViewById(R.id.user_username);
        user_password = (EditText) findViewById(R.id.user_password);
        user_cnfrm_password = (EditText) findViewById(R.id.user_cnfrm_password);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                user_up.setVisibility(View.VISIBLE);
                doc_up.setVisibility(View.GONE);
                user.setImageResource(R.drawable.user1);
                doc.setImageResource(R.drawable.doc);
            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                user_up.setVisibility(View.GONE);
                doc_up.setVisibility(View.VISIBLE);
                user.setImageResource(R.drawable.user);
                doc.setImageResource(R.drawable.doc1);
            }
        });

        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        doc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, CameraPreview.class);
                startActivity(intent);
                //finish();
            }
        });

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
                tx.setText(sdf.format(myCalendar.getTime()));
                tx1.setText(sdf.format(myCalendar.getTime()));
            }
        };

        user_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                        tx.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        doc_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                        tx1.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
                //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();
            }
        });

        doc_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup = (RadioGroup) findViewById(R.id.radio);
                // find the radiobutton by returned id
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                final String drname = doc_name.getText().toString();
                final String hospital = hospital_name.getText().toString();
                final String dremail = doc_email.getText().toString();
                final String drmobile = doc_phone_no.getText().toString();
                final String drusername = doc_username.getText().toString();
                final String drpassword = doc_password.getText().toString();
                final String drconfirmpassword = doc_cnfrm_password.getText().toString();
                final String gender = (String) radioButton.getText();
                Toast.makeText(getApplicationContext(),
                        gender, Toast.LENGTH_SHORT).show();

                if (drconfirmpassword.equals(drpassword)) {
                    insertintodoctordatabase(drname, hospital, dremail, drmobile, drusername, drpassword, gender);
                } else {
                    Toast.makeText(getApplicationContext(), "Password not matching. ReEnter the password", Toast.LENGTH_SHORT).show();

                }

            }
        });

        user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioGroup = (RadioGroup) findViewById(R.id.radio1);
                // find the radiobutton by returned id
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                final String firstname = user_namef.getText().toString();
                final String lastname = user_namel.getText().toString();
                final String email = user_email.getText().toString();
                final String mobile = user_phone_no.getText().toString();
                final String username = user_username.getText().toString();
                final String password = user_password.getText().toString();
                final String confirmpassword = user_cnfrm_password.getText().toString();
                final String gender = (String) radioButton.getText();
                Toast.makeText(getApplicationContext(),
                        gender, Toast.LENGTH_SHORT).show();

                if (confirmpassword.equals(password)) {
                    insertintouserdatabase(firstname, lastname, "123", "21", password, "M", email, "emaillogin", mobile, username);
                } else {
                    Toast.makeText(getApplicationContext(), "Password not matching. ReEnter the password", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void insertintouserdatabase(String firstname, String lastname, String dob, String age
            , String password, String gender, String email, String via, String mobile, String username) {
        InputStream is = null;
        int i = 0;
        Toast.makeText(getApplicationContext(), "insert into database", Toast.LENGTH_SHORT).show();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("firstname", firstname));
        nameValuePairs.add(new BasicNameValuePair("lastname", lastname));
        nameValuePairs.add(new BasicNameValuePair("dob", dob));
        nameValuePairs.add(new BasicNameValuePair("age", age));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("via", via));
        nameValuePairs.add(new BasicNameValuePair("gender", gender));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobile));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://172.16.96.196/medistats/Signup_User.php");  //TODO
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignUp.this, UserHome.class);
            this.startActivity(intent);
        } catch (ClientProtocolException e) {
            Log.e("ClientProtocol", "Log_tag");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Log_tag", "IOException");
            e.printStackTrace();
        }


    }


//INSERTING INTO DOCTOR DATABASE


    public void insertintodoctordatabase(String name, String hospital, String email, String mobile, String username, String password, String gender) {
        InputStream is = null;
        int i = 0;
        Toast.makeText(getApplicationContext(), "insert into database", Toast.LENGTH_SHORT).show();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name", name));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("gender", gender));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
        nameValuePairs.add(new BasicNameValuePair("hospital", hospital));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://172.16.102.87/medistats/Signup_User.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SignUp.this, DocHome.class);
            this.startActivity(intent);
        } catch (ClientProtocolException e) {
            Log.e("ClientProtocol", "Log_tag");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Log_tag", "IOException");
            e.printStackTrace();
        }


    }


/*
    public void userexists()
    {
        Username=(EditText)findViewById(R.id.uname);
        x=(TextView)findViewById(R.id.x);
        String uname=""+Username.getText().toString();
        HashMap<String, String> hash= new  HashMap<>();
        hash.put("Shubham",uname);

        com.example.shubham.newsreader.RequestHandler Rh = new com.example.shubham.newsreader.RequestHandler();
        String s=Rh.sendPostRequest("http://localhost/shubham/username.php",hash);
        if(s.equals("Success"))
        {
            Toast.makeText(getApplicationContext(),"Username Taken",Toast.LENGTH_LONG).show();
        }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

    }
    */

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
