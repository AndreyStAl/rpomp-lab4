package com.example.lab2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface bdTableInterface {
    @Query("SELECT * FROM bdTable")
    List<bdTable> getAll();

    @Insert
    void insert(bdTable table);

    @Query("DELETE FROM bdTable")
    void delete();
}
