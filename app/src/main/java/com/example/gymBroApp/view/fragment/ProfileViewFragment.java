package com.example.gymBroApp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymBroApp.R;
import com.example.gymBroApp.adapter.SettingsAdapter;
import com.example.gymBroApp.model.User;
import com.example.gymBroApp.viewModel.UserViewModel;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;


public class ProfileViewFragment extends Fragment {


    private SettingsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Integer> imgList;
    private ArrayList<String> infoList;
    private UserViewModel mModel;
    private FirebaseUser mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_view_layout, container, false);
        recyclerView = view.findViewById(R.id.recyclerSettings);


        mModel = new ViewModelProvider(this).get(UserViewModel.class);

        imgList = new ArrayList<>();
        infoList = new ArrayList<>();

        imgList.add(R.drawable.profile_icon);
        imgList.add(R.drawable.calendar_icon);
        imgList.add(R.drawable.phone_icon);
        imgList.add(R.drawable.map_icon);
        imgList.add(R.drawable.workout_icon);
        imgList.add(R.drawable.cloudy_icon);

        mModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                int i = mModel.getAllUsers().getValue().size()-1;

                infoList.add(mModel.getAllUsers().getValue().get(i).getName());
                infoList.add(String.valueOf(mModel.getAllUsers().getValue().get(i).getAge()));
                infoList.add(String.valueOf(mModel.getAllUsers().getValue().get(i).getPhoneNr()));
                infoList.add(mModel.getAllUsers().getValue().get(i).getLocation());
                infoList.add(mModel.getAllUsers().getValue().get(i).getWorkPref());
                infoList.add(mModel.getAllUsers().getValue().get(i).getWeatherPref());
                update();

            }
        });



        initRecyclerView();

        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SettingsAdapter(getContext(),infoList,imgList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void update(){
        adapter = new SettingsAdapter(getContext(),infoList,imgList);
        recyclerView.setAdapter(adapter);
    }

}
