package com.example.android.medistats;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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


public class SignUp extends AppCompatActivity  implements View.OnClickListener {
    private static final String TAG = "SignupActivity";
    //Doctor sign up page
    private static final String URL = "192.168.43.224";
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

    private static final String UserSignUp = "http://"+URL+"/ratcoder/Signup_User.php";
    private static final String DoctorSignUp = "http://"+URL+"/ratcoder/Signup_Doctor.php";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
        tx.setOnClickListener(new View.OnClickListener() {
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
        tx1.setOnClickListener(new View.OnClickListener() {
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
        doc_signup.setOnClickListener(this);
        user_signup.setOnClickListener(this);

    }

    public void insertintouserdatabase(String firstname, String lastname, String dob, String age,
                                       String password, String gender, String email, String via, String mobile, String username)
    {
        InputStream is=null;
        int i=0;
        //Toast.makeText(getApplicationContext(),"insert into database",Toast.LENGTH_SHORT).show();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("firstname",firstname));
        nameValuePairs.add(new BasicNameValuePair("lastname",lastname));
        nameValuePairs.add(new BasicNameValuePair("dob",dob));
        nameValuePairs.add(new BasicNameValuePair("age",age));
        nameValuePairs.add(new BasicNameValuePair("password",password));
        nameValuePairs.add(new BasicNameValuePair("via",via));
        nameValuePairs.add(new BasicNameValuePair("gender",gender));
        nameValuePairs.add(new BasicNameValuePair("email",email));
        nameValuePairs.add(new BasicNameValuePair("username",username));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
        Log.i("shubham","inside insert");

        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(UserSignUp);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            is=entity.getContent();
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(), UserHome.class);
            this.startActivity(intent);
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


//INSERTING INTO DOCTOR DATABASE


    public void insertintodoctordatabase(String name, String hospital, String email, String mobile, String username, String password, String gender)
    {
        InputStream is=null;
        int i=0;
        String id="shubham";

        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("id",id));
        nameValuePairs.add(new BasicNameValuePair("gender",gender));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
        nameValuePairs.add(new BasicNameValuePair("email",email));
        nameValuePairs.add(new BasicNameValuePair("username",username));
        nameValuePairs.add(new BasicNameValuePair("password",password));
        nameValuePairs.add(new BasicNameValuePair("hospital", hospital));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(DoctorSignUp);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), DocHome.class);
            startActivity(intent);
        } catch (ClientProtocolException e) {
            Log.e("ClientProtocol", "Log_tag");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Log_tag", "IOException");
            e.printStackTrace();
        }
        Log.i("shubham","inside insert 1");
    }


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SignUp.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View view) {



        if (view == doc_signup) {

            radioGroup = (RadioGroup) findViewById(R.id.radio);
            selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);

            final String drname = doc_name.getText().toString();
            final String hospital = hospital_name.getText().toString();
            final String dremail = doc_email.getText().toString();
            final String drmobile = doc_phone_no.getText().toString();
            final String drusername = doc_username.getText().toString();
            final String drpassword = doc_password.getText().toString();
            final String drconfirmpassword = doc_cnfrm_password.getText().toString();
            final String gender = (String) radioButton.getText();

            if(validate_email(dremail)) {

                if (validate_phone_no(drmobile)) {

                    //  if(validate_password_length(drpassword))
                    //{
                    if (drconfirmpassword.equals(drpassword)) {
                        Log.i("shubham","insert called");
                        insertintodoctordatabase(drname, hospital, dremail, drmobile, drusername, drpassword, gender);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password not matching. ReEnter the password", Toast.LENGTH_SHORT).show();

                    }
                    // }
                    // else {
                    //   Toast.makeText(getApplicationContext(), "Password incorrect", Toast.LENGTH_SHORT).show();

//                        }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Phone no invalid", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT).show();

            }

        }
        else if (view == user_signup) {

            radioGroup = (RadioGroup) findViewById(R.id.radio1);
            selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);

            final String firstname = user_namef.getText().toString();
            final String lastname = user_namel.getText().toString();
            final String email = user_email.getText().toString();
            final String mobile = user_phone_no.getText().toString();
            final String username = user_username.getText().toString();
            final String password = user_password.getText().toString();
            final String confirmpassword = user_cnfrm_password.getText().toString();
            final String gender = (String) radioButton.getText();

            if(validate_email(email)) {

                if (validate_phone_no(mobile)) {

                    //  if(validate_password_length(drpassword))
                    //{
                    if (confirmpassword.equals(password)) {
                        Log.i("shubham","insert called");
                        insertintouserdatabase(firstname, lastname, "1237-98-88", "21", password, gender, email, "123", mobile, username);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password not matching. ReEnter the password", Toast.LENGTH_SHORT).show();

                    }
                    // }
                    // else {
                    //   Toast.makeText(getApplicationContext(), "Password incorrect", Toast.LENGTH_SHORT).show();

//                        }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Phone no invalid", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT).show();

            }

        }

    }

    private final static boolean validate_email(CharSequence target)
    {
        if (target == null) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }

    }


    private final static boolean validate_phone_no(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();

    }

    /*
        private static boolean validate_password_length( final String password)
        {
            Pattern pattern;
            Matcher matcher;
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);

            return matcher.matches();

        }
    */

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
