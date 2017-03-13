package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.MainActivity;
import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_Dialog_Accomodation;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_show_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.view.AccommodationListItem;

import java.util.ArrayList;

public class AccommodationListAdapter extends BaseAdapter {

    private FragmentActivity MainActivity;
    private Screen_add_detail_of_days MainControl;
    private Screen_Dialog_Accomodation Control_Main_Dialog;
    private ArrayList<AccommodationListDao> AccomByCostLimit = new ArrayList<>();
    private Screen_show_detail_of_days ControlMainScreenShow = null;
    private TextView tvNotFound;

    @Override
    public int getCount() {
//        if (AccommodationListManager.getInstance().getDao() == null)
//            return 0;
//        if (AccommodationListManager.getInstance().getDao().getData() == null)
//            return 0;
//        return AccommodationListManager.getInstance().getDao().getData().size(); // get size of data
        if (AccomByCostLimit == null)
            return 0;

        return AccomByCostLimit.size(); // get size of data
    }

    @Override
    public Object getItem(int position) {
        return AccommodationListManager.getInstance().getDao().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AccommodationListItem item;

        if(AccomByCostLimit.size()>0)
        {
            tvNotFound.setVisibility(View.GONE);
        }
        else{
            tvNotFound.setVisibility(View.VISIBLE);
        }

        if(convertView != null)
            item = (AccommodationListItem) convertView;
        else
            item = new AccommodationListItem(parent.getContext());
//        final AccommodationListDao dao = (AccommodationListDao) getItem(position);
        final AccommodationListDao dao = AccomByCostLimit.get(position);
        item.setIvImgAccommodationText(dao.getImg());
        item.setTvNameAccommodationText(dao.getName());
        item.setTvDetailAccommodationText(dao.getDetail());
        item.setTvCostAccommodationText(dao.getPrice());

        item.getIvMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMap = new Intent(MainActivity, MapActivity.class);
                openMap.putExtra("ID", dao.getId());
                openMap.putExtra("TYPE_ID", Type_id_item.TYPE_ACCOMMODATION);
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

        item.getIvAdd().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(MainControl!=null) {

                            MainControl.addItemToAccom(String.valueOf(dao.getId()));
                            MainControl.setPointAccom(Double.parseDouble(dao.getLat()), Double.parseDouble(dao.getLng()));
                            Control_Main_Dialog.dismiss();
                        }else{
                            ControlMainScreenShow.addItemToAccom(String.valueOf(dao.getId()));
                            ControlMainScreenShow.setPointAccom(Double.parseDouble(dao.getLat()), Double.parseDouble(dao.getLng()));
                            Control_Main_Dialog.dismiss();
                        }
            }
        });

        return item;
    }


    public void setActivity(FragmentActivity activity, Screen_Dialog_Accomodation screen_dialog_accomodation) {
        MainActivity = activity;
        Control_Main_Dialog = screen_dialog_accomodation;
    }

    public  void setMainControl(Screen_add_detail_of_days controlMainScreen){
        MainControl = controlMainScreen;

        /**
         * get Accom with Cost Limit
         */
        getAccomByCostLimit();
    }

    public void getAccomByCostLimit(){
        AccomByCostLimit = AccommodationListManager.getInstance().getDao().getAccomByCostLimit();

    }


    public void setTextView(TextView tvNotFound) {
        this.tvNotFound = tvNotFound;
    }

    public void setMainControlShow(Screen_show_detail_of_days controlMainScreenShow) {
        ControlMainScreenShow = controlMainScreenShow;
    }
}
