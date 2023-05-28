package com.example.todoapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {
    //    private LiveData<List<Todo>> todoList;
    private UserRepository userRepository;

    private LiveData<User> userList;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }

    public void saveRegisteredUser(User user) {
        userRepository.insertUser(user);
    }
//
//    public LiveData<User> getUserList(){
//        return userList;
//    }
//

//    public LiveData<List<Todo>> getTodoList(int categoryId) {
//        todoList = repository.loadAllTodo(categoryId);
//        return todoList;
//    }
}

