package com.example.gads;

public class ApiUtil {
    private  ApiUtil(){}
    private static final String BASE_URL="https://docs.google.com/forms/d/e/";
    public static  ApiPost getApiPost(){
        return RetrofitClassPost.getRetrofitInstancePost(BASE_URL).create(ApiPost.class);
    }
}
