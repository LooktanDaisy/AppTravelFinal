package com.application.youngdeveloper.apptravelfinal.dao;

import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.manager.CostLimit;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class RestaurantListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("restaurant")     private List<RestaurantListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<RestaurantListDao> getData() {
        return data;
    }

    public void setData(List<RestaurantListDao> data) {
        this.data = data;
    }

    public ArrayList<RestaurantListDao> getRestaurantByCostLimit() {
        ArrayList<RestaurantListDao> RestaurantByCost = new ArrayList<RestaurantListDao>();

        for(int i=0;i<getData().size();i++){
            if(getData().get(i).getPrice() <= CostLimit.FoodCost){
                RestaurantByCost.add(getData().get(i));
            }
        }

        /**
         * Restaurant sort by how far from Accom
         */

//        MainFunction.SortByCostRestaurant(RestaurantByCost);
        return RestaurantByCost;
    }

    public ArrayList<RestaurantListDao> CalculateHowFarToAccom(ArrayList<RestaurantListDao> restaurantLists,Double AccomLat,Double AccomLng){

        for(int i=0;i<restaurantLists.size();i++){
            restaurantLists.get(i).setHowfarToAccom(MainFunction.distance(AccomLat,Double.parseDouble(restaurantLists.get(i).getLat()),AccomLng,Double.parseDouble(restaurantLists.get(i).getLng())));
        }

        restaurantLists = MainFunction.SortByDistanceRestaurant(restaurantLists);

        return  restaurantLists;
    }
}
