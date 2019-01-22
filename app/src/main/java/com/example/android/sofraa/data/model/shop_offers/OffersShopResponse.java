
package com.example.android.sofraa.data.model.shop_offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffersShopResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private OfferShopData offerShopData;

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

    public OfferShopData getOfferShopData() {
        return offerShopData;
    }

    public void setData(OfferShopData offerShopData) {
        this.offerShopData = offerShopData;
    }

}
