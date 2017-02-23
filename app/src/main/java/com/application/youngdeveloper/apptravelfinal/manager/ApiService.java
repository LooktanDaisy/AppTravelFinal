package com.application.youngdeveloper.apptravelfinal.manager;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Wachiraya_Kam on 2/21/2017.
 */

public interface ApiService {
    @POST("android_select_place.php")
    Call<PlaceListCollectionDao> loadPlaceList(); //don't start URL with "/"

    @POST("android_select_Restaurant.php")
    Call<RestaurantListCollectionDao> loadRestaurantList();

    @POST("android_select_accommodation.php")
    Call<AccommodationListCollectionDao> loadAccommodation();
}
