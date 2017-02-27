package com.application.youngdeveloper.apptravelfinal.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.MainActivity;
import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.manager.User;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Screen_register extends AppCompatActivity implements View.OnClickListener {

    private EditText edText_name;
    private EditText edText_email;
    private EditText edText_password;
    private EditText edText_confirmpassword;
    private Button btn_register;
    private String strPassword;
    private String strCfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_register);

        initialView();
    }

    private void initialView() {
        edText_name = (EditText) findViewById(R.id.edText_name);
        edText_email = (EditText) findViewById(R.id.edText_email);
        edText_password = (EditText) findViewById(R.id.edText_password);
        edText_confirmpassword = (EditText) findViewById(R.id.edText_confirmpassword);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view == btn_register) {
            if (CheckBlankEditText(edText_name)) {
                if (CheckBlankEditText(edText_email)) {
                    if (CheckBlankEditText(edText_password)) {
                        if (CheckBlankEditText(edText_confirmpassword)) {

                            /**
                             * Check correct blank
                             */

                            strPassword = edText_password.getText().toString();
                            strCfPassword = edText_confirmpassword.getText().toString();

                            //TODO: Check confirm password
                            if (strCfPassword.equals(strPassword)) {
                                CheckRegister();
                            }
                            else {
                                Toast.makeText(this.getApplicationContext(), R.string.E04, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
    }


    private boolean CheckBlankEditText(EditText edittext) {
        String textFromEdittex = edittext.getText().toString();
        if (textFromEdittex.isEmpty() || textFromEdittex.contains(" ")) {
            Toast.makeText(this.getApplicationContext(), R.string.E02, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void CheckRegister() {
        try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", edText_name.getText().toString().trim()));
                nameValuePairs.add(new BasicNameValuePair("email", edText_email.getText().toString().trim()));
                nameValuePairs.add(new BasicNameValuePair("password", edText_password.getText().toString().trim()));


                //TODO: Register
                /* Set to Http post*/
                /* End set Value*/
                HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_insertuser.php");
                /* End Set URL*/
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String responseText = httpclient.execute(httppost, responseHandler);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (responseText.contains("registration error")) {
                        Log.d("responseText", "regis error"+ responseText);
                        /**
                         * Register Fail check by echo from Server
                         */
                        Toast.makeText(getApplicationContext(), R.string.E05, Toast.LENGTH_SHORT).show();

                        Log.d("responseText", "Exception : " + responseText);

                    } else {
//                        String[] dataText = responseText.split(",");
//                        Log.d("responseText", "Data From Sever : " + responseText);
//
//                        //response NAME,EMAIL,PASSWORD
//                        User.NAME = dataText[0];
//                        User.EMAIL = dataText[1];
//                        User.PASSWORD = dataText[2];
//
//                        /**
//                         * Login Success
//                         */

                        Toast.makeText(getApplicationContext(), R.string.register_success, Toast.LENGTH_SHORT).show();
                        ShowLoginScreenAfterRegister();

//                        /**
//                         * Register Fail check by echo from Server
//                         */
//
//                        Log.d("responseText", "Exception : " + responseText);

                    }

                }
            });

        }catch (Exception e){
            Log.d("log_err", "Error in http connection " + e.toString());
        }
    }

    private void ShowLoginScreenAfterRegister(){
        Intent ShowBarMenuScreen = new Intent(Screen_register.this, MainActivity.class);
        ShowBarMenuScreen.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(ShowBarMenuScreen);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


}
