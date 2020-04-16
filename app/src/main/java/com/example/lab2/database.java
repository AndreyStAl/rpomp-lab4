package com.example.lab2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities= {bdTable.class}, version = 1)
public abstract class database extends RoomDatabase {
    public abstract bdTableInterface bdtableinterface();
}
