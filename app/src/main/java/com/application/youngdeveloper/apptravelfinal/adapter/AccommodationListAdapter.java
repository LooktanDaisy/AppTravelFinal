package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.youngdeveloper.apptravelfinal.MainActivity;
import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.application.youngdeveloper.apptravelfinal.view.AccommodationListItem;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class AccommodationListAdapter extends BaseAdapter {

    private FragmentActivity MainActivity;

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
        final AccommodationListDao dao = (AccommodationListDao) getItem(position);
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
