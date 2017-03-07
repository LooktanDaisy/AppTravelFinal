package com.application.youngdeveloper.apptravelfinal.manager;

import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestuarantListCollectionDao;
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


    @FormUrlEncoded
    @POST("android_select_place_plan.php")
    Call<PlanPlaceListCollectionDao> loadPlacePlanList(
            @Field("userID") String userID
    );

    @FormUrlEncoded
    @POST("android_select_accom_plan.php")
    Call<PlanAccommodationListCollectionDao> loadAccommodationPlanList(
            @Field("userID") String userID
    );

    @FormUrlEncoded
    @POST("android_select_restau_plan.php")
    Call<PlanRestuarantListCollectionDao> loadReataurantPlanList(
            @Field("userID") String userID
    );


}
