package com.example.lab2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    public Post(){}

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("cost")
    @Expose
    private String cost;

    @SerializedName("inform")
    @Expose
    private String inform;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getCountry() {
        return country;
    }

    public String getCost() {
        return cost;
    }

    public String getInform() {
        return inform;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }
}
