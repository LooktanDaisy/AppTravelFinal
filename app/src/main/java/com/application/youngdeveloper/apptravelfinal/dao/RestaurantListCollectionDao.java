package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

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
}
