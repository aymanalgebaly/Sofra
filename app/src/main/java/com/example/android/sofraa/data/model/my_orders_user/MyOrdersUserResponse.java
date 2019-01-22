
package com.example.android.sofraa.data.model.my_orders_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrdersUserResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private MyOrsersUserData myOrsersUserData;

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

    public MyOrsersUserData getMyOrsersUserData() {
        return myOrsersUserData;
    }

    public void setData(MyOrsersUserData myOrsersUserData) {
        this.myOrsersUserData = myOrsersUserData;
    }

}
