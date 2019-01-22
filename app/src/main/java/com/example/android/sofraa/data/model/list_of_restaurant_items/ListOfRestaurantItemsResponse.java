
package com.example.android.sofraa.data.model.list_of_restaurant_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfRestaurantItemsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListOfRestaurantItemsData listOfRestaurantItemsData;

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

    public ListOfRestaurantItemsData getListOfRestaurantItemsData() {
        return listOfRestaurantItemsData;
    }

    public void setData(ListOfRestaurantItemsData listOfRestaurantItemsData) {
        this.listOfRestaurantItemsData = listOfRestaurantItemsData;
    }

}
