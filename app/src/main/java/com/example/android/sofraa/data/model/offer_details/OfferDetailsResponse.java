
package com.example.android.sofraa.data.model.offer_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDetailsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private OfferDetailsData offerDetailsData;

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

    public OfferDetailsData getOfferDetailsData() {
        return offerDetailsData;
    }

    public void setData(OfferDetailsData offerDetailsData) {
        this.offerDetailsData = offerDetailsData;
    }

}
