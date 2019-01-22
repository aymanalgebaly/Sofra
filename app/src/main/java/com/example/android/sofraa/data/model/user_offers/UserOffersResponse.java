
package com.example.android.sofraa.data.model.user_offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserOffersResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserOffersData userOffersData;

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

    public UserOffersData getUserOffersData() {
        return userOffersData;
    }

    public void setData(UserOffersData data) {
        this.userOffersData = userOffersData;
    }

}
