package com.application.youngdeveloper.apptravelfinal.manager;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListCollectionDao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("android_select_place.php")
    Call<PlaceListCollectionDao> loadPlaceList(); //don't start URL with "/"

    @POST("android_select_Restaurant.php")
    Call<RestaurantListCollectionDao> loadRestaurantList();

    @POST("android_select_accommodation.php")
    Call<AccommodationListCollectionDao> loadAccommodation();

    @FormUrlEncoded
    @POST("android_select_plan.php")
    Call<PlanListCollectionDao> loadPlanList(
            @Field("userID") String userID
    );


}
