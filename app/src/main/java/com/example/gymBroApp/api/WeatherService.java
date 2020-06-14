package com.example.gymBroApp.api;



import com.example.gymBroApp.view.fragment.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q")String q,@Query(",dk") String a, @Query("APPID")String app_id);
}
