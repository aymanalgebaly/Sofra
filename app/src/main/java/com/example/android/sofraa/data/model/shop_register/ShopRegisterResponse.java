
package com.example.android.sofraa.data.model.shop_register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopRegisterResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private RegisterData registerData;

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

    public RegisterData getRegisterData() {
        return registerData;
    }

    public void setData(RegisterData registerData) {
        this.registerData = registerData;
    }

}
