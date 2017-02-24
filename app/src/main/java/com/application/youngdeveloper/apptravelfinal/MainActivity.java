package com.application.youngdeveloper.apptravelfinal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.adapter.AccommodationListAdapter;
import com.application.youngdeveloper.apptravelfinal.adapter.PlaceListAdapter;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_Container_bar;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_register;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Create View
    private EditText edt_user,edt_password;
    private Button btn_login;
    private TextView tv_register;
    private FrameLayout frameLayout;
    PlaceListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        frameLayout = (FrameLayout) findViewById(R.id.first_open_anim);
        initialView();

        //downloadDataFromServer();

    }




    @Override
    protected void onStart() {
        super.onStart();


        downloadDataFromServer();

            frameLayout.animate()
                    .translationY(frameLayout.getHeight())
                    .alpha(0.0f)
                    .setDuration(2500)
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

        btn_login.setEnabled(false);
        btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue_trans));
        btn_login.setTextColor(getResources().getColor(R.color.white_trans));
        tv_register.setEnabled(false);
        tv_register.setTextColor(getResources().getColor(R.color.white_trans));
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


    private void downloadDataFromServer() {

        /**
         * Download Place
         */

        Call<PlaceListCollectionDao> call = HttpManager.getInstance().getService().loadPlaceList();
        call.enqueue(new Callback<PlaceListCollectionDao>() { //Asynchronous
            @Override
            public void onResponse(Call<PlaceListCollectionDao> call,
                                   Response<PlaceListCollectionDao> response) {

                if(response.isSuccessful()){
                    PlaceListCollectionDao dao = response.body();
                    PlaceListManager.getInstance().setDao(dao);
                    //listAdapter.notifyDataSetChanged();


                    /**
                     * if connect server Enable button
                     */
                    btn_login.setEnabled(true);
                    btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue));
                    btn_login.setTextColor(getResources().getColor(R.color.white));
                    tv_register.setEnabled(true);
                    tv_register.setTextColor(getResources().getColor(R.color.white));

                } else{
                    // Handle
                    try {
                        Toast.makeText(MainActivity.this, "โหลดข้อมูลสถานที่ท่องเที่ยวไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<PlaceListCollectionDao> call, Throwable t) {
                // Handle

                Toast.makeText(MainActivity.this, "โหลดข้อมูลสถานที่ท่องเที่ยวไม่สำเร็จ",Toast.LENGTH_LONG).show();

                Log.d("ggg",t.toString());

//                btn_login.setEnabled(false);
//                btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue_trans));
//                tv_register.setEnabled(false);
            }
        });

        /**
         * Show Restaurant
         */

        Call<RestaurantListCollectionDao> callRestaurant = HttpManager.getInstance().getService().loadRestaurantList();
        callRestaurant.enqueue(new Callback<RestaurantListCollectionDao>() { //Asynchronous
            @Override
            public void onResponse(Call<RestaurantListCollectionDao> call,
                                   Response<RestaurantListCollectionDao> response) {

                if(response.isSuccessful()){
                    RestaurantListCollectionDao dao = response.body();
                    RestaurantListManager.getInstance().setDao(dao);
                    //listAdapter.notifyDataSetChanged();

                } else{
                    // Handle
                    try {
                        Toast.makeText(MainActivity.this, "โหลดข้อมูลร้านอาหารไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<RestaurantListCollectionDao> call, Throwable t) {
                // Handle

                Toast.makeText(MainActivity.this, R.string.err_loadRestaurant,Toast.LENGTH_LONG).show();

                Log.d("ggg",t.toString());

//                btn_login.setEnabled(false);
//                btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue_trans));
//                tv_register.setEnabled(false);


            }
        });

        /**
         * Show Accommodation
         */

        Call<AccommodationListCollectionDao> callAccommodation = HttpManager.getInstance().getService().loadAccommodation();
        callAccommodation.enqueue(new Callback<AccommodationListCollectionDao>() { //Asynchronous
            @Override
            public void onResponse(Call<AccommodationListCollectionDao> call,
                                   Response<AccommodationListCollectionDao> response) {

                if(response.isSuccessful()){
                    AccommodationListCollectionDao dao = response.body();
                    AccommodationListManager.getInstance().setDao(dao);
                    //listAdapter.notifyDataSetChanged();

                } else{
                    // Handle
                    try {
                        Toast.makeText(MainActivity.this, R.string.err_loadAccommodation, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AccommodationListCollectionDao> call, Throwable t) {
                // Handle

                Toast.makeText(MainActivity.this, R.string.err_loadAccommodation,Toast.LENGTH_LONG).show();

                Log.d("ggg",t.toString());

//                btn_login.setEnabled(false);
//                btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue_trans));
//                tv_register.setEnabled(false);
            }
        });
    }



}
