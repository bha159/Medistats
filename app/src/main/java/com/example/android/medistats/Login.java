/*package com.example.android.medistats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.android.medistats.SessionManager.KEY_NAME;
import static com.example.shubham.newsreader.SessionManager.KEY_NAME;


public class Login extends AppCompatActivity
{
    String myJSON;
    JSONArray peoples=null;
    ArrayList<HashMap<String,String>> personList;
    com.example.shubham.newsreader.SessionManager session;
    ListView list;
    EditText Name,etpass;
    Button insert;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.second);
        Name=(EditText)findViewById(R.id.name1);


        session = new com.example.shubham.newsreader.SessionManager(getApplicationContext());


        etpass=(EditText)findViewById(R.id.pass1);

        insert=(Button)findViewById(R.id.but1);
        if(session.isLoggedIn())
        {
            HashMap<String, String> user = new HashMap<String, String>();
            user=session.getUserDetails();
            String name=user.get(KEY_NAME);

            Log.i("LOGIN","hello");

            Log.i("LOGGED IN",name);

            Intent intent = new Intent(this, MainActivity.class);
            //String name=Name.getText().toString();
            intent.putExtra("name",name);
            this.startActivity(intent);
        }
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        insert.setOnClickListener(new OnClickListener(){
            InputStream is=null;
            int i=0;
            @Override
            public void onClick(View ar)
            {
                String name=""+Name.getText().toString();
                String pass=""+etpass.getText().toString();

                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("name",name));

                nameValuePairs.add(new BasicNameValuePair("pass",pass));
                String result=null;
                try
                {
                    HttpClient httpClient=new DefaultHttpClient(new BasicHttpParams());
                    HttpPost httpPost=new HttpPost("http://192.168.43.224/login.php");
                    InputStream inputStream=null;

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response=httpClient.execute(httpPost);
                    HttpEntity entity=response.getEntity();
                    is=entity.getContent();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                    StringBuilder sb=new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!=null)
                    {
                        sb.append(line+"\n");
                    }
                    result=sb.toString();

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
                finally {
                    try{if(is!=null)is.close();}
                    catch(Exception squish){}
                }

                gett(result,name,pass);

            }
        });

    }

    public void gett(String result,String name,String pass){
        myJSON=result;


        int p;
        //Log.i("shreeya",myJSON);

        myJSON = myJSON.replace("\n", "").replace("\r", "");
        if(myJSON.equals("Success")) {

            session.createLoginSession(name,pass);
            Toast.makeText(getApplicationContext(),"Welcome"+" "+Name.getText(), Toast.LENGTH_LONG).show();
            Log.i("LOGIN",Name.getText().toString());

            Intent intent = new Intent(this, MainActivity.class);
            String name1=Name.getText().toString();
            intent.putExtra("name",name1);

            this.startActivity(intent);
        }
        else
        {
            //Toast.makeText(getApplicationContext(), myJSON, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Login.class);
            this.startActivity(intent);
        }

    }
    public void onClick(View v)
    {
        if(v.getId()==R.id.text6)
        {
            Intent intent=new Intent(this,ThirdActivity.class);
            this.startActivity(intent);

        }
    }
}
*/