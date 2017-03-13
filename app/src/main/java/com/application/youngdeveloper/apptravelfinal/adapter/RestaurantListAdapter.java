package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_Dialog_Restaurant;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_show_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.view.AccommodationListItem;
import com.application.youngdeveloper.apptravelfinal.view.RestaurantListItem;

import java.util.ArrayList;

public class RestaurantListAdapter extends BaseAdapter {
    private FragmentActivity MainActivity;
    private Screen_add_detail_of_days MainControl;
    private Screen_Dialog_Restaurant Control_Main_Dialog;
    private ArrayList<RestaurantListDao> RestaurantByCostLimit = new ArrayList<>();
    private Screen_show_detail_of_days ControlMainScreenShow = null;
    private TextView tvNotfound;


    @Override
    public int getCount() {
//        if (RestaurantListManager.getInstance().getDao() == null)
//            return 0;
//        if (RestaurantListManager.getInstance().getDao().getData() == null)
//            return 0;
//        return RestaurantListManager.getInstance().getDao().getData().size(); // get size of data

        if (RestaurantByCostLimit == null)
            return 0;

        return RestaurantByCostLimit.size(); // get size of data
    }

    @Override
    public Object getItem(int position) {
        return RestaurantListManager.getInstance().getDao().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantListItem item;

        if(RestaurantByCostLimit.size() > 0) {
            tvNotfound.setVisibility(View.GONE);
        }
        else
        {
            tvNotfound.setVisibility(View.VISIBLE);
        }

        if(convertView != null)
            item = (RestaurantListItem) convertView;
        else
            item = new RestaurantListItem(parent.getContext());
//        final RestaurantListDao dao = (RestaurantListDao) getItem(position);

        final RestaurantListDao dao = RestaurantByCostLimit.get(position);

        item.setIvImgRestaurantText(dao.getImg());
        item.setTvNameRestaurantText(dao.getName());
        item.setTvDetailRestaurantText(dao.getDetail());
        item.setTvCostRestaurantText(dao.getPrice());
        item.setHowFar(dao.getHowfarToAccom());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMap = new Intent(MainActivity, MapActivity.class);
                openMap.putExtra("ID",dao.getId());
                openMap.putExtra("TYPE_ID", Type_id_item.TYPE_RESTAURANT);
                if(MainControl!=null) {
                    openMap.putExtra("ACCOM_LAT", MainControl.getAccomLat());
                    openMap.putExtra("ACCOM_LNG", MainControl.getAccomLng());
                }else{
                    openMap.putExtra("ACCOM_LAT", ControlMainScreenShow.getAccomLat());
                    openMap.putExtra("ACCOM_LNG", ControlMainScreenShow.getAccomLng());
                }

                MainActivity.startActivity(openMap);
                MainActivity.overridePendingTransition(R.anim.fade_in_fast,R.anim.fade_out_fast);
            }
        });

        item.getIvAdd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainControl!=null) {

                    MainControl.addItemToRestaurant(String.valueOf(dao.getId()));
                    Control_Main_Dialog.dismiss();
                }else{
                    ControlMainScreenShow.addItemToRestaurant(String.valueOf(dao.getId()));
                    Control_Main_Dialog.dismiss();
                }


            }
        });

        return item;
    }

    public void setActivity(FragmentActivity activity, Screen_Dialog_Restaurant screen_dialog_restaurant) {
        MainActivity = activity;
        Control_Main_Dialog = screen_dialog_restaurant;
    }

    public void setMainControl(Screen_add_detail_of_days controlMainScreen){
        MainControl = controlMainScreen;
        if (MainControl!=null) {
            getRestaurantByCostLimit();
        }
    }

    public void getRestaurantByCostLimit(){
        RestaurantByCostLimit = RestaurantListManager.getInstance().getDao().getRestaurantByCostLimit();

        /**
         * Calculate How Far and Sort
         */
        if(MainControl!=null) {
            RestaurantByCostLimit = RestaurantListManager.getInstance().getDao().CalculateHowFarToAccom(RestaurantByCostLimit, MainControl.getAccomLat(), MainControl.getAccomLng());
        }else{
            RestaurantByCostLimit = RestaurantListManager.getInstance().getDao().CalculateHowFarToAccom(RestaurantByCostLimit, ControlMainScreenShow.getAccomLat(), ControlMainScreenShow.getAccomLng());
        }
    }

    public void setTextView(TextView tvNotFound) {
        this.tvNotfound = tvNotFound;

    }

    public void setMainControlShow(Screen_show_detail_of_days controlMainScreenShow) {
        ControlMainScreenShow = controlMainScreenShow;
        if(ControlMainScreenShow!=null) {
            getRestaurantByCostLimit();
        }
    }
}
