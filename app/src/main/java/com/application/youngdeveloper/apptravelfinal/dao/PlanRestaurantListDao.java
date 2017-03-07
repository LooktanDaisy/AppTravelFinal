package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PlanRestaurantListDao {

    @SerializedName("plan_id")
    private int planID;
    @SerializedName("restaurant_id")
    private int restauID;
    @SerializedName("date")
    private Date date;
    @SerializedName("traveler_id")
    private int traveler_id;

    public int getTraveler_id() {
        return traveler_id;
    }

    public void setTraveler_id(int traveler_id) {
        this.traveler_id = traveler_id;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getRestauID() {
        return restauID;
    }

    public void setRestauID(int placeID) {
        this.restauID = placeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
