
package com.example.android.sofraa.data.model.list_of_restaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListOfRestaurantResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ListOfRestaurantsData listOfRestaurantsData;

    private Pivot pivot;

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

    public ListOfRestaurantsData getListOfRestaurantsData() {
        return listOfRestaurantsData;
    }

    public void setData(ListOfRestaurantsData listOfRestaurantsData) {
        this.listOfRestaurantsData = listOfRestaurantsData;
    }

    public Pivot pivot(){
        return pivot;
    }
    public void setPivot(Pivot pivot){
        this.pivot=pivot;
    }

}
