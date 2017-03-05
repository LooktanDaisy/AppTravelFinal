package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.view.PlaceListItem;

import java.util.List;

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


    public PlaceListDao getPlace(int id_Place){
        PlaceListDao PLACE = null;
        List<PlaceListDao> ListPLACE =  getDao().getData();
        int i;
        for(i = 0;i<ListPLACE.size();i++){
            if(ListPLACE.get(i).getId() == id_Place){
                PLACE = ListPLACE.get(i);
            }
        }

        return PLACE;
    }
}
