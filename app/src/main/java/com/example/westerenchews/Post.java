package com.example.westerenchews;

import com.example.westerenchews.test.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Post {



    @POST("login")
    @FormUrlEncoded
    Call<String> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @POST("register")
    @FormUrlEncoded
    Call<String> register(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address
    );


    @POST("getProducts")
    @FormUrlEncoded
    Call<ProductsResponse> getProducts(
            @Field("orderid") String orderid
    );

    @POST("orders")
    @FormUrlEncoded
    Call<String> orders(
            @Field("email") String email,
            @Field("orderid") String orderid
    );
}
