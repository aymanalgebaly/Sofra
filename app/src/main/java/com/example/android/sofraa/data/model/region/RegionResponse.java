
package com.example.android.sofraa.data.model.region;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private RegionData regionData;

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

    public RegionData getRegionData() {
        return regionData;
    }

    public void setData(RegionData regionData) {
        this.regionData = regionData;
    }

}
