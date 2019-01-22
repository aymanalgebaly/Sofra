
package com.example.android.sofraa.data.model.commission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommissionResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private CommissionData commissionData;

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

    public CommissionData getCommissionData() {
        return commissionData;
    }

    public void setData(CommissionData commissionData) {
        this.commissionData = commissionData;
    }

}
