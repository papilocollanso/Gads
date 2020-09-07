package com.example.gads;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClassPost {

    private static Retrofit retrofit=null;



    public static Retrofit getRetrofitInstancePost(String baseUrl){
        if(retrofit ==null){
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;

    }
}
