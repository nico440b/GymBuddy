package com.example.gymBroApp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymBroApp.model.User;
import com.example.gymBroApp.repo.UserRepo;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepo repo;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repo = new UserRepo(application);
        allUsers = repo.getAllUsers();
    }

    public void insert(User user){
        repo.insertUser(user);
    }

    public void update(User user){
        repo.updateUser(user);
    }

    public void delete(User user){
        repo.deleteUser(user);
    }

    public void deleteAll(){
        repo.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
