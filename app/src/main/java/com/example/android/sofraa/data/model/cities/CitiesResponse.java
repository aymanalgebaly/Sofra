
package com.example.android.sofraa.data.model.cities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitiesResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private CitiesData citiesData;

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

    public CitiesData getCitiesData() {
        return citiesData;
    }

    public void setData(CitiesData citiesData) {
        this.citiesData = citiesData;
    }

}
