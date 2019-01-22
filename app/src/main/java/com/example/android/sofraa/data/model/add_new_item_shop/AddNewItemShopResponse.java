
package com.example.android.sofraa.data.model.add_new_item_shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewItemShopResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AddNewItemData addNewItemData;

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

    public AddNewItemData getAddNewItemData() {
        return addNewItemData;
    }

    public void setData(AddNewItemData addNewItemData) {
        this.addNewItemData = addNewItemData;
    }

}
