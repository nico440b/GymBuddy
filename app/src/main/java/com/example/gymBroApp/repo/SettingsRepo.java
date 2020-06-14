package com.example.gymBroApp.repo;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class SettingsRepo {
    private static SettingsRepo instance;
    private ArrayList<String> list = new ArrayList<>();


    public static SettingsRepo getInstance(){
        if(instance==null){
            instance = new SettingsRepo();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<String>> getData(){
        setData();
        MutableLiveData<ArrayList<String>> data = new MutableLiveData<>();
        data.setValue(list);
        return data;
    }

    private void setData(){
        list.add("Nicolai");
        list.add(String.valueOf(24));
        list.add(String.valueOf(22324548));
        list.add("Aarhus");
        list.add("Crossfit");
        list.add("Rainy");
    }

}
