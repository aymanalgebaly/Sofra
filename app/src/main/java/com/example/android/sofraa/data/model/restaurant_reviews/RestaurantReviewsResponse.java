
package com.example.android.sofraa.data.model.restaurant_reviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantReviewsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private RestaurantReviewsData restaurantReviewsData;

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

    public RestaurantReviewsData getRestaurantReviewsData() {
        return restaurantReviewsData;
    }

    public void setData(RestaurantReviewsData restaurantReviewsData) {
        this.restaurantReviewsData = restaurantReviewsData;
    }

}
