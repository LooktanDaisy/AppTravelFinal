package com.application.youngdeveloper.apptravelfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.screen.Screen_register;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Create View
    private EditText edt_user,edt_password;
    private Button btn_login;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        initialView();
    }

    private void initialView() {
        edt_user = (EditText) findViewById(R.id.edText_username);
        edt_password = (EditText) findViewById(R.id.edText_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


            if(view == tv_register){
                Intent callRegisterScreen = new Intent(MainActivity.this, Screen_register.class);
                startActivity(callRegisterScreen);
            }




    }
}
