
package com.example.android.sofraa.data.model.shop_products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ProductsData productsData;

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

    public ProductsData getProductsData() {
        return productsData;
    }

    public void setData(ProductsData productsData) {
        this.productsData = productsData;
    }

}
