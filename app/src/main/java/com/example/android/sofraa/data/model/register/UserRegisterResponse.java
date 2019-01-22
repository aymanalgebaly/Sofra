
package com.example.android.sofraa.data.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private UserRegisterData userRegisterData;

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

    public UserRegisterData getUserRegisterData() {
        return userRegisterData;
    }

    public void setData(UserRegisterData userRegisterData) {
        this.userRegisterData = userRegisterData;
    }

}
