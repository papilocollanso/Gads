package com.example.gads;

import com.google.gson.annotations.SerializedName;

public class RetrofitSkills {
    @SerializedName("name")
    private  String name;
    @SerializedName("score")
    private Integer score;
    @SerializedName("country")
    private String country;
    @SerializedName("batchUrl")
    private String batchUrl;

    public RetrofitSkills(String name, Integer score, String country, String batchUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.batchUrl = batchUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBatchUrl() {
        return batchUrl;
    }
}
