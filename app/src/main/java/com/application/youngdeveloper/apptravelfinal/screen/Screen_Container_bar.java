package com.application.youngdeveloper.apptravelfinal.screen;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.application.youngdeveloper.apptravelfinal.R;

public class Screen_Container_bar extends AppCompatActivity {

    static String TAG = "Screen_Container_bar";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_menu_bar);


        setFragmentToContainer();


    }

    private void setFragmentToContainer() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Screen_Home.newInstance(),TAG).commit();

    }
}
