package com.example.gymBroApp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymBroApp.R;
import com.example.gymBroApp.adapter.WeatherAdapter;
import com.example.gymBroApp.model.SettingsItem;
import com.example.gymBroApp.viewModel.WeatherViewModel;

import java.util.ArrayList;

public class WeatherViewFragment extends Fragment {

    private WeatherViewModel mWeather;
    private RecyclerView recyclerView;
    private ArrayList<SettingsItem> list;
    private WeatherAdapter adapter;
    private ProgressBar pbar;
    private ImageButton btn;
    private EditText text;
    private Button searchBtn;
    public String location;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_view_layout, container, false);
        recyclerView = view.findViewById(R.id.recyclerWeather);

        mWeather = new ViewModelProvider(this).get(WeatherViewModel.class);
        location = "Aarhus";
        mWeather.init(location);

        list = new ArrayList<>();

        text = view.findViewById(R.id.locationInput);
        pbar = view.findViewById(R.id.progressBarW);
        btn = view.findViewById(R.id.refreshBtn);
        searchBtn = view.findViewById(R.id.weatherSearchBtn);

        mWeather.loadData().observe(getViewLifecycleOwner(), new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                pbar.setVisibility(View.VISIBLE);
                update();
                pbar.setVisibility(View.INVISIBLE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pbar.setVisibility(View.VISIBLE);
                mWeather.reset();
                location = text.getText().toString();
                mWeather.init(location);
                mWeather.loadData().getValue();
                mWeather.loadData().observe(getViewLifecycleOwner(), new Observer<WeatherResponse>() {
                    @Override
                    public void onChanged(WeatherResponse weatherResponse) {
                        update();
                    }
                });

                pbar.setVisibility(View.INVISIBLE);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
                location = text.getText().toString();
                mWeather.init(location);
                mWeather.loadData().getValue();
                mWeather.loadData().observe(getViewLifecycleOwner(), new Observer<WeatherResponse>() {
                    @Override
                    public void onChanged(WeatherResponse weatherResponse) {
                        update();
                    }
                });
                pbar.setVisibility(View.INVISIBLE);
            }
        });

        initRecyclerView();
        return view;
    }
    public void initRecyclerView(){

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new WeatherAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        /*DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider_2));
        recyclerView.addItemDecoration(dividerItemDecoration);*/
    }
    public void update(){

        float temp = mWeather.loadData().getValue().main.temp;
        float tempCelsius;
        tempCelsius = (float) 272.15;

        if (list.size()!=0){
            list.clear();
        }
        list.add(new SettingsItem(R.drawable.map_icon,mWeather.loadData().getValue().name));
        list.add(new SettingsItem(R.drawable.thermo_icon,(Math.round(temp-tempCelsius))+"\u2103"));
        list.add(new SettingsItem(R.drawable.cloudy_icon,mWeather.loadData().getValue().weather.get(0).main));
        list.add(new SettingsItem(R.drawable.gauge_icon,(mWeather.loadData().getValue().main.pressure/1000)+" bar"));
        list.add(new SettingsItem(R.drawable.humidity_icon,String.valueOf(mWeather.loadData().getValue().main.humidity)+" %"));
        list.add(new SettingsItem(R.drawable.wind_icon,(mWeather.loadData().getValue().wind.speed)+" m/s"));
        adapter = new WeatherAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

}
