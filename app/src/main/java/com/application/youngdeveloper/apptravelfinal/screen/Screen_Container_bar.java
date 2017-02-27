package com.application.youngdeveloper.apptravelfinal.screen;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Colors;

public class Screen_Container_bar extends AppCompatActivity implements View.OnClickListener {

    static String TAG = "Screen_Container_bar";

    private RelativeLayout menu_home,menu_add_plan,menu_my_plan,menu_profile;
    private TextView tvHome,tvAddPlan,tvMyPlan,tvProfile;
    private ImageView imgHome,imgAddPlan,imgMyPlan,imgProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_menu_bar);


        setFragmentToContainer();
        initialView();

    }

    private void initialView() {
        menu_home = (RelativeLayout) findViewById(R.id.menu1);
        menu_home.setOnClickListener(this);
        menu_add_plan = (RelativeLayout) findViewById(R.id.menu2);
        menu_add_plan.setOnClickListener(this);
        menu_my_plan = (RelativeLayout) findViewById(R.id.menu3);
        menu_my_plan.setOnClickListener(this);
        menu_profile = (RelativeLayout) findViewById(R.id.menu4);
        menu_profile.setOnClickListener(this);

        tvHome = (TextView) findViewById(R.id.textViewHome) ;
        imgHome = (ImageView) findViewById(R.id.imageHome);

        tvAddPlan = (TextView) findViewById(R.id.textViewAddPlan) ;
        imgAddPlan = (ImageView) findViewById(R.id.imageAddPlan);

        tvMyPlan = (TextView) findViewById(R.id.textViewMyPlan) ;
        imgMyPlan = (ImageView) findViewById(R.id.imageMyPlan);

        tvProfile = (TextView) findViewById(R.id.textViewProfile) ;
        imgProfile = (ImageView) findViewById(R.id.imageProfile);

        setColorActive(tvHome,imgHome);
        setColorUnActive(tvAddPlan,imgAddPlan);
        setColorUnActive(tvMyPlan,imgMyPlan);
        setColorUnActive(tvProfile,imgProfile);

    }



    private void setFragmentToContainer() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Home.newInstance(),TAG).commit();

    }

    @Override
    public void onClick(View view) {

        if(view == menu_home){
            setColorActive(tvHome,imgHome);
            setColorUnActive(tvAddPlan,imgAddPlan);
            setColorUnActive(tvMyPlan,imgMyPlan);
            setColorUnActive(tvProfile,imgProfile);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Home.newInstance(),TAG).commit();


        }else if(view == menu_add_plan){
            setColorUnActive(tvHome,imgHome);
            setColorActive(tvAddPlan,imgAddPlan);
            setColorUnActive(tvMyPlan,imgMyPlan);
            setColorUnActive(tvProfile,imgProfile);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Add_Plan.newInstance(),TAG).commit();


        }else if(view == menu_my_plan){
            setColorUnActive(tvHome,imgHome);
            setColorUnActive(tvAddPlan,imgAddPlan);
            setColorActive(tvMyPlan,imgMyPlan);
            setColorUnActive(tvProfile,imgProfile);


//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Add_Plan.newInstance(),TAG).commit();


        }else if(view == menu_profile){
            setColorUnActive(tvHome,imgHome);
            setColorUnActive(tvAddPlan,imgAddPlan);
            setColorUnActive(tvMyPlan,imgMyPlan);
            setColorActive(tvProfile,imgProfile);


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Account.newInstance(),TAG).commit();

        }

    }


    private void setColorActive(TextView textMenu,ImageView imgMenu){
        textMenu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        Drawable img_Menu = imgMenu.getDrawable();
        img_Menu.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
    }

    private void setColorUnActive(TextView textMenu,ImageView imgMenu){
        textMenu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.grey_unactive));
        Drawable img_Menu = imgMenu.getDrawable();
        img_Menu.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.grey_unactive), PorterDuff.Mode.SRC_ATOP);
    }


    @Override
    public void onBackPressed() {

        /**
         * Show dialog when user back press
         */

        final Dialog dialog = new Dialog(Screen_Container_bar.this, R.style.DialogFullScreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit_program);
        dialog.setCancelable(false);

        dialog.getWindow().getAttributes().windowAnimations = R.style.Dialog_animation;

        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialog.dismiss();
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }
}
