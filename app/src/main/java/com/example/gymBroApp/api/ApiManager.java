package com.example.gymBroApp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.gymBroApp.view.fragment.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Api code from https://www.c-sharpcorner.com/article/how-to-create-weather-app-using-retrofit-2-in-android/

public class ApiManager {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "e104ec54df7ce3ea7b96a4fc10bae852";
    public static String Country = ",dk";



    void getCurrentData(final MutableLiveData<WeatherResponse> wR, String location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(location,Country, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    wR.setValue(weatherResponse);
                    assert weatherResponse != null;


                    System.out.println("----Getting DATA----");
                }

            }
            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                System.out.println("FAILURE"+t);
            }
        });
    }



}
