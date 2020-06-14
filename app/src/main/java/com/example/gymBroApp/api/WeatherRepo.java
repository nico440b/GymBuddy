package com.example.gymBroApp.api;

import androidx.lifecycle.MutableLiveData;

import com.example.gymBroApp.view.fragment.WeatherResponse;

public class WeatherRepo {

    private static WeatherRepo instance;
    private ApiManager am = new ApiManager();

    public static WeatherRepo getInstance(){
        if(instance==null){
            instance = new WeatherRepo();
        }
        return instance;
    }

    public static void deleteInstance(){
        instance = null;
    }

    public MutableLiveData<WeatherResponse> getData(String location){
        MutableLiveData<WeatherResponse> data = new MutableLiveData<>();
        am.getCurrentData(data, location);
        return data;
    }

}
