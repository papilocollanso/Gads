package com.example.gads;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;

public interface ApiService {
    @GET("/api/hours")
    Call<List<RetroLeaders>> getAllLeaders();
    @GET("/api/skilliq")
    Call<List<RetrofitSkills>> getALLSkills();
}
