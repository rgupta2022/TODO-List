package com.example.todoapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    //    private LiveData<List<Todo>> todoList;
    private UserRepository userRepository;

    private LiveData<User> user;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
//        user = userRepository.getUserList();
    }

    public LiveData<User> getUser(String email, String password) {
        return userRepository.getUserList(email, password);
    }
}

