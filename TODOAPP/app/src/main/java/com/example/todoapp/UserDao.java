package com.example.todoapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    LiveData<User> loadUserDetails(String email, String password);

    @Delete
    void deleteUser(User user);

//    @Query("delete from user")
//    void deleteAllUser();
//
//    @Query("delete from user where isCompleted=1")
//    void deleteAllCompleted();
//
//    @Query("update todo set isCompleted=1 where todoId = :todoId")
//    void completeTask(int todoId);
//
//    @Query("select * from todo where todoId = :todoId")
//    LiveData<Todo> loadTodoById(int todoId);
//
//    @Query("select * from todo where categoryId = :categoryId")
//    LiveData<List<Todo>> loadTodoByCategoryId(int categoryId );
}
