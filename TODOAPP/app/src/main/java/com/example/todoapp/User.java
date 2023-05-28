package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

//@Entity(foreignKeys = @ForeignKey(entity = Category.class,
// parentColumns = "categoryId", childColumns = "categoryId"))
@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String fullname;

    private String email;

    private String password;

    private String phoneNo;


    public User() {
    }

    @Ignore
    public User(String fullname, String email, String password, String phoneNo) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
