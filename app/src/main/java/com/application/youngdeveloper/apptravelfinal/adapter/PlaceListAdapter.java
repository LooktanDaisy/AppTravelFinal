package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.application.youngdeveloper.apptravelfinal.view.PlaceListItem;

/**
 * Created by Wachiraya_Kam on 2/22/2017.
 */

public class PlaceListAdapter extends BaseAdapter {

    private FragmentActivity MainActivity;

    @Override
    public int getCount() {
        if (PlaceListManager.getInstance().getDao() == null)
            return 0;
        if (PlaceListManager.getInstance().getDao().getData() == null)
            return 0;
        return PlaceListManager.getInstance().getDao().getData().size(); // get size of data
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
        if (convertView != null)
            item = (PlaceListItem) convertView;
        else
            item = new PlaceListItem(parent.getContext());
        final PlaceListDao dao = (PlaceListDao) getItem(position);
        item.setIvImgPlaceText(dao.getImg());
        item.setTvNamePlaceText(dao.getName());
        item.setTvAddressPlaceText(dao.getDetail());
        item.setTvCostPlaceText(dao.getCost());

        /**
         * Set onclick to map ImageView
         */
        item.getIvMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMap = new Intent(MainActivity, MapActivity.class);
                openMap.putExtra("ID_PLACE",dao.getId());
                MainActivity.startActivity(openMap);
                MainActivity.overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
            }
        });


        return item;
    }

    public void setActivity(FragmentActivity activity) {
        MainActivity = activity;
    }
}
