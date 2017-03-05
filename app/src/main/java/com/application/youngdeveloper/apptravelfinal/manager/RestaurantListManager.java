package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;

import java.util.List;

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


    public RestaurantListDao getRestaurant(int id_Restaurant) {
        RestaurantListDao RESTAURANT = null;
        List<RestaurantListDao> ListRESTAURANT = getDao().getData();
        int i;
        for (i = 0; i < ListRESTAURANT.size(); i++) {
            if (ListRESTAURANT.get(i).getId() == id_Restaurant) {
                RESTAURANT = ListRESTAURANT.get(i);
            }
        }
        return RESTAURANT;
    }
}