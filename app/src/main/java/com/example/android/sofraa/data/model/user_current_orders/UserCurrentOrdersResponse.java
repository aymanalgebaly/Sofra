
package com.example.android.sofraa.data.model.user_current_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCurrentOrdersResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserCurrentOdrdersData userCurrentOdrdersData;

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

    public UserCurrentOdrdersData getUserCurrentOdrdersData() {
        return userCurrentOdrdersData;
    }

    public void setData(UserCurrentOdrdersData userCurrentOdrdersData) {
        this.userCurrentOdrdersData = userCurrentOdrdersData;
    }

}
