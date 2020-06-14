package com.example.gymBroApp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.gymBroApp.adapter.UserAdapter;
import com.example.gymBroApp.model.User;
import com.example.gymBroApp.viewModel.UserViewModel;

import java.util.List;


public class FindViewFragment extends Fragment {

    private UserViewModel viewModel;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ProgressBar pbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_view_layout, container, false);
        recyclerView = view.findViewById(R.id.findRecyclerView);
        pbar = view.findViewById(R.id.findPbar);


        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                pbar.setVisibility(View.VISIBLE);
                update();
                pbar.setVisibility(View.INVISIBLE);
            }
        });



        initRecyclerView();
        pbar.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        pbar.setVisibility(View.VISIBLE);
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new UserAdapter(getContext(),viewModel.getAllUsers().getValue());
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void update(){
        adapter = new UserAdapter(getContext(),viewModel.getAllUsers().getValue());
        recyclerView.setAdapter(adapter);
    }

}
