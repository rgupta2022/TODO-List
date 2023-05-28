package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;


    private LiveData<User> currentUser;


    public void insertUser(User user){
        new UserRepository.InsertUser(userDao).execute(user);
    }

//    public LiveData<User> loadUser(String email,String password){
//        return new UserRepository.SelectUser(userDao).execute(email,password);
//    }

    public UserRepository(Application application)
    {
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
//        currentUser = userDao.loadUserDetails();

    }

    private static class InsertUser extends AsyncTask<User,Void,Void> {

        private UserDao userDao;

        public InsertUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {

            userDao.insertUser(users[0]);

            return null;
        }
    }

    public LiveData<User> getUserList(String email, String password) {
        return userDao.loadUserDetails(email, password);
    }

}
