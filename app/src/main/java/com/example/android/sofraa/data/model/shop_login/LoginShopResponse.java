
package com.example.android.sofraa.data.model.shop_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginShopResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ShopLoginData shopLoginData;

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

    public ShopLoginData getShopLoginData() {
        return shopLoginData;
    }

    public void setData(ShopLoginData shopLoginData) {
        this.shopLoginData = shopLoginData;
    }

}
