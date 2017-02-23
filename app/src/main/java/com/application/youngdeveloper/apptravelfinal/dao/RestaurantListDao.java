package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class RestaurantListDao {
    @SerializedName("id")           private int id;
    @SerializedName("name")         private String name;
    @SerializedName("lat")          private String lat;
    @SerializedName("lng")          private String lng;
    @SerializedName("detail")       private String detail;
    @SerializedName("price")        private int price;
    @SerializedName("img")          private String img;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
