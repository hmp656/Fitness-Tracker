package com.prodpingu.fitnesstracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrackerDao {
    @Insert
    void insert(Tracker tracker);

    @Update
    void update(Tracker tracker);

    @Delete
    void delete(Tracker tracker);

    @Query("SELECT * FROM tracker_table ORDER BY dateEntry DESC")
    LiveData<List<Tracker>> getAllEntries();
}
