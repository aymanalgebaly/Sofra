
package com.example.android.sofraa.data.model.shop_offers_new;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopOffersResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ShopOffersDataNew shopOffersDataNew;

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

    public ShopOffersDataNew getShopOffersDataNew() {
        return shopOffersDataNew;
    }

    public void setData(ShopOffersDataNew shopOffersDataNew) {
        this.shopOffersDataNew = shopOffersDataNew;
    }

}
