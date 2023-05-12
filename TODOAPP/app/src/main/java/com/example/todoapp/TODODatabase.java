package com.example.todoapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = TODO.class,version = 5)
@TypeConverters({DateConverter.class,TimeConverter.class})
public abstract class TODODatabase extends RoomDatabase {

    private static TODODatabase instance;

    public abstract TodoDao todoDao();
    public static  synchronized TODODatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
            ,TODODatabase.class,"todo_database").fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
