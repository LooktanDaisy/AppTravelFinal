package com.application.youngdeveloper.apptravelfinal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.screen.Screen_Container_bar;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_register;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Create View
    private EditText edt_user,edt_password;
    private Button btn_login;
    private TextView tv_register;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        frameLayout = (FrameLayout) findViewById(R.id.first_open_anim);
        initialView();
    }


    @Override
    protected void onStart() {
        super.onStart();


        frameLayout.animate()
                .translationY(frameLayout.getHeight())
                .alpha(0.0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        frameLayout.setVisibility(View.GONE);
                    }
                });

    }

    private void initialView() {
        edt_user = (EditText) findViewById(R.id.edText_username);
        edt_password = (EditText) findViewById(R.id.edText_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


            if(view == tv_register){
                Intent callRegisterScreen = new Intent(MainActivity.this, Screen_register.class);
                startActivity(callRegisterScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }else if(view == btn_login){
                /**
                 * Check blank and Valid with method
                 */

                if(CheckBlankEditText(edt_user)){
                    if(CheckBlankEditText(edt_password)){

                        /**
                         * Check correct Here and call Main Screen
                         */

                        Intent ShowBarMenuScreen = new Intent(MainActivity.this, Screen_Container_bar.class);
                        ShowBarMenuScreen.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(ShowBarMenuScreen);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                        finish();

                    }
                }
            }




    }

    private boolean CheckBlankEditText(EditText edittext) {
        String textFromEdittex = edittext.getText().toString();
        if(textFromEdittex.isEmpty() || textFromEdittex.contains(" ")){
            Toast.makeText(this.getApplicationContext(),R.string.E01,Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
