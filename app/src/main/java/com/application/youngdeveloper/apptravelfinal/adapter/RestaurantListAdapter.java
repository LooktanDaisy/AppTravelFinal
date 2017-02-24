package com.application.youngdeveloper.apptravelfinal.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.view.AccommodationListItem;
import com.application.youngdeveloper.apptravelfinal.view.RestaurantListItem;

/**
 * Created by Wachiraya_Kam on 2/24/2017.
 */

public class RestaurantListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if (RestaurantListManager.getInstance().getDao() == null)
            return 0;
        if (RestaurantListManager.getInstance().getDao().getData() == null)
            return 0;
        return RestaurantListManager.getInstance().getDao().getData().size(); // get size of data
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
        if(convertView != null)
            item = (RestaurantListItem) convertView;
        else
            item = new RestaurantListItem(parent.getContext());
        RestaurantListDao dao = (RestaurantListDao) getItem(position);
        item.setIvImgRestaurantText(dao.getImg());
        item.setTvNameRestaurantText(dao.getName());
        item.setTvDetailRestaurantText(dao.getDetail());
        item.setTvCostRestaurantText(dao.getPrice());

        return item;
    }
}
