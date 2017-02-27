package com.application.youngdeveloper.apptravelfinal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.adapter.PlaceListAdapter;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanListManager;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.manager.User;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_Container_bar;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_register;

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

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Create View
    private EditText edt_user, edt_password;
    private Button btn_login;
    private TextView tv_register;
    private FrameLayout frameLayout;
    PlaceListAdapter listAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        /**
         * set for connect http
         */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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


        if (view == tv_register) {
            Intent callRegisterScreen = new Intent(MainActivity.this, Screen_register.class);
            startActivity(callRegisterScreen);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        } else if (view == btn_login) {
            /**
             * Check blank and Valid with method
             */

            if (CheckBlankEditText(edt_user)) {
                if (CheckBlankEditText(edt_password)) {

                    /**
                     * Check correct Here and call Main Screen
                     */

                    frameLayout.setVisibility(View.VISIBLE);
                    CheckLogin();

                }
            }
        }


    }

    private void ShowMainScreenAfterLogin(){
        Intent ShowBarMenuScreen = new Intent(MainActivity.this, Screen_Container_bar.class);
        ShowBarMenuScreen.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(ShowBarMenuScreen);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    private boolean CheckBlankEditText(EditText edittext) {
        String textFromEdittex = edittext.getText().toString();
        if (textFromEdittex.isEmpty() || textFromEdittex.contains(" ")) {
            Toast.makeText(this.getApplicationContext(), R.string.E01, Toast.LENGTH_SHORT).show();
            return false;
        } else {
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

                if (response.isSuccessful()) {
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

                } else {
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

                Toast.makeText(MainActivity.this, "โหลดข้อมูลสถานที่ท่องเที่ยวไม่สำเร็จ", Toast.LENGTH_LONG).show();

                Log.d("ggg", t.toString());

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

                if (response.isSuccessful()) {
                    RestaurantListCollectionDao dao = response.body();
                    RestaurantListManager.getInstance().setDao(dao);
                    //listAdapter.notifyDataSetChanged();

                } else {
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

                Toast.makeText(MainActivity.this, R.string.err_loadRestaurant, Toast.LENGTH_LONG).show();

                Log.d("ggg", t.toString());

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

                if (response.isSuccessful()) {
                    AccommodationListCollectionDao dao = response.body();
                    AccommodationListManager.getInstance().setDao(dao);
                    //listAdapter.notifyDataSetChanged();

                } else {
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

                Toast.makeText(MainActivity.this, R.string.err_loadAccommodation, Toast.LENGTH_LONG).show();

                Log.d("ggg", t.toString());

//                btn_login.setEnabled(false);
//                btn_login.setBackground(getDrawable(R.drawable.border_button_dark_blue_trans));
//                tv_register.setEnabled(false);
            }
        });


    }


    private void CheckLogin() {

        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", edt_user.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("password", edt_password.getText().toString().trim()));

                /* Set to Http post*/
                /* End set Value*/
            HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
            HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_checklogin.php");
                /* End Set URL*/
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String responseText = httpclient.execute(httppost, responseHandler);

            runOnUiThread(new Runnable() {

                public void run() {

                    if (responseText.contains("wrong username or password")) {

                        /**
                         * Login Fail check by echo from Server
                         */

                        Toast.makeText(getApplicationContext(), R.string.E01, Toast.LENGTH_SHORT).show();

                        Log.d("responseText", "Exception : " + responseText);

                    } else {

                        String[] dataText = responseText.split(",");
                        Log.d("responseText", "Data From Sever : " + responseText);

                        //response ID,NAME,EMAIL
                        User.ID = dataText[0];
                        User.NAME = dataText[1];
                        User.EMAIL = dataText[2];

                        /**
                         * Login Success
                         */
                        DownloadUserPlanOnserver();
//                        ShowMainScreenAfterLogin();

                    }
                }
            });

        } catch (Exception e) {
            Log.d("log_err", "Error in http connection " + e.toString());

        }



    }

    private void DownloadUserPlanOnserver() {
        /**
         * Download Place
         */

        Call<PlanListCollectionDao> call = HttpManager.getInstance().getService().loadPlanList(User.ID);
        call.enqueue(new Callback<PlanListCollectionDao>() { //Asynchronous
            @Override
            public void onResponse(Call<PlanListCollectionDao> call,
                                   Response<PlanListCollectionDao> response) {

                if (response.isSuccessful()) {
                    PlanListCollectionDao dao = response.body();
                    if(dao.getData().size()>0) {
                    PlanListManager.getInstance().setDao(dao);
                    }

                    /**
                     * Show main screen when Loaded Plan
                     */
                    ShowMainScreenAfterLogin();

                } else {
                    // Handle
                    try {
                        Toast.makeText(MainActivity.this, "โหลดข้อมูลแผนการท่องเที่ยวไม่สำเร็จ", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<PlanListCollectionDao> call, Throwable t) {
                // Handle

                Toast.makeText(MainActivity.this, "โหลดข้อมูลแผนการท่องเที่ยวไม่สำเร็จ", Toast.LENGTH_LONG).show();

                Log.d("LOAD PLAN Error", t.toString());


            }
        });
    }

}
