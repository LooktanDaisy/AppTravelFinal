package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class AccommodationListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("accommodation")     private List<AccommodationListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<AccommodationListDao> getData() {
        return data;
    }

    public void setData(List<AccommodationListDao> data) {
        this.data = data;
    }
}
