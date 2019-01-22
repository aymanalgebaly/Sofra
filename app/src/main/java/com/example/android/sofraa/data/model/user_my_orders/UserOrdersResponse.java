
package com.example.android.sofraa.data.model.user_my_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserOrdersResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserCurruntUserData userCurruntUserData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserCurruntUserData getUserCurruntUserData() {
        return userCurruntUserData;
    }

    public void setData(UserCurruntUserData userCurruntUserData) {
        this.userCurruntUserData = userCurruntUserData;
    }

}
