package com.prodpingu.fitnesstracker;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Entity(tableName = "tracker_table")
public class Tracker {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "dateEntry")
    private long datetimeToday = new Date().getTime();

    private int weightToday;

    private int heightToday;

    public Tracker(int weightToday, int heightToday, long datetimeToday) {

        if (datetimeToday != 0) {
            this.datetimeToday = datetimeToday;
        }
        this.weightToday = weightToday;
        this.heightToday = heightToday;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public int getWeightToday() {
        return weightToday;
    }

    public int getHeightToday() {
        return heightToday;
    }

    public long getDatetimeToday() {
        return datetimeToday;
    }
}
