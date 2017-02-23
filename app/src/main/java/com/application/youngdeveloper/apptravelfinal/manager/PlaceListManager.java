package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;

/**
 * Created by Wachiraya_Kam on 2/22/2017.
 */

public class PlaceListManager {
    private static PlaceListManager instance;

    public static PlaceListManager getInstance() {
        if (instance == null)
            instance = new PlaceListManager();
        return instance;
    }

    private Context mContext;
    private PlaceListCollectionDao dao;

    private PlaceListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PlaceListCollectionDao getDao() {
        return dao;
    }

    public void setDao(PlaceListCollectionDao dao) {
        this.dao = dao;
    }
}
