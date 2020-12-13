
package com.example.westerenchews.test;

import java.util.List;

import com.example.westerenchews.Product;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProductsResponse {

    @SerializedName("data")
    private List<Product> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("res")
    private String mRes;

    public List<Product> getData() {
        return mData;
    }

    public void setData(List<Product> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getRes() {
        return mRes;
    }

    public void setRes(String res) {
        mRes = res;
    }

}
