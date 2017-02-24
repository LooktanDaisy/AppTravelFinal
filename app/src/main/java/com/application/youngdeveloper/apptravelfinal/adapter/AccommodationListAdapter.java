package com.application.youngdeveloper.apptravelfinal.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.view.AccommodationListItem;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class AccommodationListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        if (AccommodationListManager.getInstance().getDao() == null)
            return 0;
        if (AccommodationListManager.getInstance().getDao().getData() == null)
            return 0;
        return AccommodationListManager.getInstance().getDao().getData().size(); // get size of data
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
        AccommodationListItem item;
        if(convertView != null)
            item = (AccommodationListItem) convertView;
        else
            item = new AccommodationListItem(parent.getContext());
        AccommodationListDao dao = (AccommodationListDao) getItem(position);
        item.setIvImgAccommodationText(dao.getImg());
        item.setTvNameAccommodationText(dao.getName());
        item.setTvDetailAccommodationText(dao.getDetail());
        item.setTvCostAccommodationText(dao.getPrice());

        return item;
    }
}
