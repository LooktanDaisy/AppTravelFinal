package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by theerawat on 2/27/2017 AD.
 */

public class PlanAccommodationListDao {
    @SerializedName("plan_id")
    private int planID;
    @SerializedName("accommodation_id")
    private int placeID;
    @SerializedName("date")
    private Date date;

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
