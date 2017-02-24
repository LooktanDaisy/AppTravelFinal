package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class AccommodationListManager {
    private static AccommodationListManager instance;

    public static AccommodationListManager getInstance() {
        if (instance == null)
            instance = new AccommodationListManager();
        return instance;
    }

    private Context mContext;
    private AccommodationListCollectionDao dao;

    private AccommodationListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public AccommodationListCollectionDao getDao() {
        return dao;
    }

    public void setDao(AccommodationListCollectionDao dao) {
        this.dao = dao;
    }
}
