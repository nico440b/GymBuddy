package com.example.gymBroApp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;


public class FindViewFragment extends Fragment {

    private UserViewModel viewModel;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ProgressBar pbar;
    CarouselView carouselView;
    ImageListener imageListener;

    int[] sampleImages = {R.drawable.user1_pic,R.drawable.user2_pic,R.drawable.user3_pic,R.drawable.user4_pic};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_view_layout, container, false);

            carouselView = (CarouselView) view.findViewById(R.id.carouselView);
            carouselView.setPageCount(sampleImages.length);
            carouselView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);


            carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(sampleImages[position]);
                }
            });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }


}
