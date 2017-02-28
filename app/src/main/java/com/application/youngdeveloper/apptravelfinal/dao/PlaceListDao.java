package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wachiraya_Kam on 2/21/2017.
 */

public class PlaceListDao {
    @SerializedName("id")           private int id;
    @SerializedName("place_type_name") private String nameType;
    @SerializedName("name")         private String name;
    @SerializedName("lat")          private String lat;
    @SerializedName("lng")          private String lng;
    @SerializedName("detail")       private String detail;
    @SerializedName("img")          private String img;
    @SerializedName("cost")         private int cost;

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

