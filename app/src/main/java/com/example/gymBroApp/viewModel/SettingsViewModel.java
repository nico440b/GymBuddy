package com.example.gymBroApp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymBroApp.repo.SettingsRepo;

import java.util.ArrayList;

public class SettingsViewModel extends ViewModel {
    private SettingsRepo repo;

    private MutableLiveData<ArrayList<String>> weather;

    public void init (){
        if (weather != null){
            return;
        }

        repo = SettingsRepo.getInstance();
        weather = repo.getData();

    }

    public LiveData<ArrayList<String>> loadData(){
        return weather;
    }

}
