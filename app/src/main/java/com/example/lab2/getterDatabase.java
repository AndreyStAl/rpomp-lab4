package com.example.lab2;

import android.content.Context;

import androidx.room.Room;

public class getterDatabase {

    private static database instance=null;

    public static database getInstance(Context context) {
        if(instance==null)
            instance = Room.databaseBuilder(context, database.class, "db1")
                    .build();
        return instance;
    }
}