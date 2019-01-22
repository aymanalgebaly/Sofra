
package com.example.android.sofraa.settings_media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SettingsMediaResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private SettingsMediaData settingsMediaData;

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

    public SettingsMediaData getData() {
        return settingsMediaData;
    }

    public void setData(SettingsMediaData settingsMediaData) {
        this.settingsMediaData = settingsMediaData;
    }

}
