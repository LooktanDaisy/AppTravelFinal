package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;

/**
 * Created by Wachiraya_Kam on 2/24/2017.
 */

public class RestaurantListManager {

    private static RestaurantListManager instance;

    public static RestaurantListManager getInstance() {
        if (instance == null)
            instance = new RestaurantListManager();
        return instance;
    }

    private Context mContext;
    private RestaurantListCollectionDao dao;

    private RestaurantListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public RestaurantListCollectionDao getDao() {
        return dao;
    }

    public void setDao(RestaurantListCollectionDao dao) {
        this.dao = dao;
    }
}
