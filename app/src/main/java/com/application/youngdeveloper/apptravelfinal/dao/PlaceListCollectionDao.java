package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wachiraya_Kam on 2/21/2017.
 */

public class PlaceListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("place")     private List<PlaceListDao> data;

    public int isSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlaceListDao> getData() {
        return data;
    }

    public void setData(List<PlaceListDao> data) {
        this.data = data;
    }
}
