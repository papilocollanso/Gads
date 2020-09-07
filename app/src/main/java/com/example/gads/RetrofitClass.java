package com.example.gads;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

    private static Retrofit retrofit;
    private static final String BASE_URL="https://gadsapi.herokuapp.com/";


    public static Retrofit getRetrofitInstance(){
        if(retrofit ==null){
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;

    }
}
