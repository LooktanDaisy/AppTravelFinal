package com.application.youngdeveloper.apptravelfinal.manager;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Wachiraya_Kam on 2/21/2017.
 */

public interface ApiService {
    @POST("android_select_place.php")
    Call<PlaceListCollectionDao> loadPlaceList(); //don't start URL with "/"
}
