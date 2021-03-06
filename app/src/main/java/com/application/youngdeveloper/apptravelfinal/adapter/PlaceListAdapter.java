package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_Dialog_Place;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_show_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.view.PlaceListItem;

import java.util.ArrayList;

public class PlaceListAdapter extends BaseAdapter {

    private FragmentActivity MainActivity;
    private Screen_add_detail_of_days MainControl;
    private Screen_Dialog_Place Control_Main_Dialog;
    private Screen_show_detail_of_days ControlMainScreenShow = null;
    private int typeId=0;
    private ArrayList<PlaceListDao> placeByType = new ArrayList<>();
    private TextView tvNotFound;

    @Override
    public int getCount() {
//        if (PlaceListManager.getInstance().getDao() == null)
//            return 0;
//        if (PlaceListManager.getInstance().getDao().getData() == null)
//            return 0;
//        return PlaceListManager.getInstance().getDao().getData().size(); // get size of data

        if (placeByType == null)
            return 0;

        return placeByType.size(); // get size of data
    }

    @Override
    public Object getItem(int position) {
        return PlaceListManager.getInstance().getDao().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PlaceListItem item;

        if (placeByType.size()>0){
            tvNotFound.setVisibility(View.GONE);
        }
        else
        {
            tvNotFound.setVisibility(View.VISIBLE);
        }

        if (convertView != null)
            item = (PlaceListItem) convertView;
        else
            item = new PlaceListItem(parent.getContext());
//        final PlaceListDao dao = (PlaceListDao) getItem(position);

            final PlaceListDao dao = placeByType.get(position);

            item.setIvImgPlaceText(dao.getImg());
            item.setTvNamePlaceText(dao.getName());
            item.setTvAddressPlaceText(dao.getDetail());
            item.setTvCostPlaceText(dao.getCost());
            item.setHowFar(dao.getHowfarToAccom());

            /**
             * Set onclick to map ImageView
             */
            item.getIvMap().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openMap = new Intent(MainActivity, MapActivity.class);
                    openMap.putExtra("ID", dao.getId());
                    openMap.putExtra("TYPE_ID", Type_id_item.TYPE_PLACE);
                    if(MainControl!=null) {
                        openMap.putExtra("ACCOM_LAT", MainControl.getAccomLat());
                        openMap.putExtra("ACCOM_LNG", MainControl.getAccomLng());
                    }else{
                        openMap.putExtra("ACCOM_LAT", ControlMainScreenShow.getAccomLat());
                        openMap.putExtra("ACCOM_LNG", ControlMainScreenShow.getAccomLng());
                    }

                    MainActivity.startActivity(openMap);
                    MainActivity.overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
                }
            });

            /**
             * Set add button
             */
            item.getIvAdd().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(MainControl!=null) {

                        MainControl.addItemToPlace(String.valueOf(dao.getId()));
                        Control_Main_Dialog.dismiss();
                    }else{
                        ControlMainScreenShow.addItemToPlace(String.valueOf(dao.getId()));
                        Control_Main_Dialog.dismiss();
                    }

//                MainActivity.finish();
//                MainActivity.overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
                }
            });

            return item;

    }

    public void setActivity(FragmentActivity activity, Screen_Dialog_Place screen_dialog_place) {
        MainActivity = activity;
        Control_Main_Dialog = screen_dialog_place;
    }

    public void setMainControl(Screen_add_detail_of_days controlMainScreen){
        MainControl = controlMainScreen;
    }

    public void selectTypePlace(int i) {
        typeId = i;
        placeByType = PlaceListManager.getInstance().getDao().getDataByTypePlace(typeId);
        if(MainControl!=null){
            placeByType = PlaceListManager.getInstance().getDao().CalculateHowFarToAccom(placeByType,MainControl.getAccomLat(),MainControl.getAccomLng());
        }else{
            placeByType = PlaceListManager.getInstance().getDao().CalculateHowFarToAccom(placeByType,ControlMainScreenShow.getAccomLat(),ControlMainScreenShow.getAccomLng());

        }
        notifyDataSetChanged();
    }


    public void setMainControlShow(Screen_show_detail_of_days controlMainScreenShow) {
        ControlMainScreenShow = controlMainScreenShow;
    }


    public void setTextView(TextView tvNotFound) {
            this.tvNotFound = tvNotFound;
    }
}
