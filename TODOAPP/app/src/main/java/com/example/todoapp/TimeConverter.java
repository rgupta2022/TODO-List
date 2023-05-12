package com.example.todoapp;

import androidx.room.TypeConverter;

import java.sql.Time;

public class TimeConverter {

    @TypeConverter
    public static Time toTime(Long value) {
        return value == null ? null : new Time(value);
    }

    @TypeConverter
    public static Long toLong(Time value) {
        return value == null ? null : value.getTime();
    }
}
