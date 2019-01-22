
package com.example.android.sofraa.data.model.shop_register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("data")
    @Expose
    private RegisterData registerData;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public RegisterData getRegisterData() {
        return registerData;
    }

    public void setData(RegisterData registerData) {
        this.registerData = registerData;
    }

}
