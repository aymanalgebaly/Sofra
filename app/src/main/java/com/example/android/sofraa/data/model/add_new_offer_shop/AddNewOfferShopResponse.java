
package com.example.android.sofraa.data.model.add_new_offer_shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewOfferShopResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AddNewOfferShopData addNewOfferShopData;

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

    public AddNewOfferShopData getAddNewOfferShopData() {
        return addNewOfferShopData ;
    }

    public void setData(AddNewOfferShopData addNewOfferShopData) {
        this.addNewOfferShopData = addNewOfferShopData;
    }

}
