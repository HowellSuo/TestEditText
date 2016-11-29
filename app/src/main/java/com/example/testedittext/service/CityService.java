package com.example.testedittext.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityService {
    @GET("location/search.json")
    Call<ResponseBody>getCitySelected(@Query("key")String key,@Query("q")String keyCity);
}
