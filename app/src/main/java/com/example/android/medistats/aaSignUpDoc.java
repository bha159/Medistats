package com.example.android.medistats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpDoc extends AppCompatActivity
{
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.doc_name) EditText docname;
    @InjectView(R.id.hospital_name) EditText hospital_name;
    @InjectView(R.id.doc_email) EditText doc_email;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupdoc);
        ButterKnife.inject(this);

//Adding Thread Policy
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//End
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup()
    {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpDoc.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        //These variables name should be same as BasicNameValuePair first parameter which is same as my php file variable.
        //Look my php files for variable names.

        String name = ""+_nameText.getText().toString();
        String email =""+ _emailText.getText().toString();
        String password = ""+_passwordText.getText().toString();




//Start
        // TODO: Implement your own signup logic here.
        InputStream is=null;
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("email",email));
        nameValuePairs.add(new BasicNameValuePair("password",password));


        try
        {
            HttpClient httpClient=new DefaultHttpClient();
            //only have to change this link. Rest all is same
            HttpPost httpPost=new HttpPost("http://192.168.43.224/signup.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            is=entity.getContent();
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();

            //Calling next activity on successfull signup
            startActivity(new Intent(SignUpDoc.this,Login.class));

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



// TODO: Implementing Username is taken or not .

        Password=(EditText)findViewById(R.id.doc_password);
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

//End

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess()
    {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed()
    {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate()
    {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
