package com.example.gymBroApp.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.gymBroApp.model.User;
import com.example.gymBroApp.database.UserDao;
import com.example.gymBroApp.database.UserDatabase;

import java.util.List;

public class UserRepo {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepo(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUser();
    }

    public void insertUser(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void deleteUser(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao dao){
            this.userDao=dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao dao){
            this.userDao=dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao dao){
            this.userDao=dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void,Void,Void>{

        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao dao){
            this.userDao=dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

}
