package com.example.lab2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class bdTable {
    @PrimaryKey
    public int id;

    public String image;

    public String country;

    public String cost;

    public String inform;

}
