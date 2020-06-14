package com.example.gymBroApp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymBroApp.api.WeatherRepo;
import com.example.gymBroApp.view.fragment.WeatherResponse;



public class WeatherViewModel extends ViewModel {

    private WeatherRepo repo;

    private String location;

    private MutableLiveData<WeatherResponse> weather;

    public void init (String location){
        this.location = location;
        if (weather != null){
            return;
        }

        repo = WeatherRepo.getInstance();
        weather = repo.getData(location);
    }

    public LiveData<WeatherResponse> loadData(){
        return weather;
    }

    public void reset(){
        WeatherRepo.deleteInstance();
    }


}

