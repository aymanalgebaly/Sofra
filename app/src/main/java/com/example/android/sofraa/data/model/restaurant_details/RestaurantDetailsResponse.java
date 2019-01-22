
package com.example.android.sofraa.data.model.restaurant_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantDetailsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private getRestaurantDetailsData getRestaurantDetailsData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public getRestaurantDetailsData getRestaurantDetailsData() {
        return getRestaurantDetailsData;
    }

    public void setData(getRestaurantDetailsData getRestaurantDetailsData) {
        this.getRestaurantDetailsData = getRestaurantDetailsData;
    }

}
