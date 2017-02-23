package com.application.youngdeveloper.apptravelfinal.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.view.PlaceListItem;

/**
 * Created by Wachiraya_Kam on 2/22/2017.
 */

public class PlaceListAdapter extends BaseAdapter {
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
        PlaceListItem item;
        if (convertView != null)
            item = (PlaceListItem) convertView;
        else
            item = new PlaceListItem(parent.getContext());
        PlaceListDao dao = (PlaceListDao) getItem(position);
        item.setIvImgPlaceText(dao.getImg());
        item.setTvNamePlaceText(dao.getName());
        item.setTvAddressPlaceText(dao.getDetail());
        item.setTvCostPlaceText(dao.getCost());
        return item;
    }
}
