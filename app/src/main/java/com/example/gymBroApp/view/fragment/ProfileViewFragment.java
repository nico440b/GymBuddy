package com.example.gymBroApp.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gymBroApp.R;
import com.example.gymBroApp.adapter.SettingsAdapter;
import com.example.gymBroApp.model.User;
import com.example.gymBroApp.view.activity.HomeActivity;
import com.example.gymBroApp.viewModel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;
import static android.app.Activity.RESULT_OK;


public class ProfileViewFragment extends Fragment {


    private SettingsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Integer> imgList;
    private ArrayList<String> infoList;
    private UserViewModel mModel;
    private FirebaseAuth mAuth;
    private Button saveBtn;
    private EditText editText;
    private Button imgBtn;
    private ImageView imgView;
    private static final int PERMISSION_REQUEST = 123;
    private LruCache<String, Bitmap> memoryCache;
    private int userID;

    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int cacheSize = maxMemory / 8;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_view_layout, container, false);



        recyclerView = view.findViewById(R.id.recyclerSettings);

        mAuth = FirebaseAuth.getInstance();
        imgBtn = view.findViewById(R.id.profilePicBtn);
        imgView = view.findViewById(R.id.profilePic);
        imgView.setImageBitmap(null);
        mModel = new ViewModelProvider(this).get(UserViewModel.class);

        imgList = new ArrayList<>();
        infoList = new ArrayList<>();
        saveBtn = view.findViewById(R.id.saveBtn);
        editText = view.findViewById(R.id.textInputSettings);

        imgList.add(R.drawable.icon_user);
        imgList.add(R.drawable.icon_pw);
        imgList.add(R.drawable.icon_mail);
        imgList.add(R.drawable.icon_phone);
        imgList.add(R.drawable.icon_calendar);
        imgList.add(R.drawable.icon_map);
        imgList.add(R.drawable.icon_muscle);
        imgList.add(R.drawable.icon_weather);




        if (memoryCache == null){
            memoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        }
        imgView.setImageBitmap(getBitmapFromMemCache("Profile Picture"));
        mModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    if (user.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                        userID = user.getId();
                        infoList.add(user.getName());
                        infoList.add(user.getPw());
                        infoList.add(user.getEmail());
                        infoList.add(String.valueOf(user.getPhoneNr()));
                        infoList.add(String.valueOf(user.getAge()));
                        infoList.add(user.getLocation());
                        infoList.add(user.getWorkPref());
                        infoList.add(user.getWeatherPref());

                    }
                }
                update();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PERMISSION_REQUEST);
            }
        });
        initRecyclerView();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_REQUEST && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            addBitmapToMemoryCache("Profile Picture",BitmapFactory.decodeFile(picturePath));
            imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SettingsAdapter(getContext(),infoList,imgList);
        recyclerView.setAdapter(adapter);
    }

    public void update(){
        adapter = new SettingsAdapter(getContext(),infoList,imgList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
            {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, do something you want
                } else {
                    // permission denied
                    Toast.makeText(getContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
            memoryCache.put(key, bitmap);

    }

    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }



}
