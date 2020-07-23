package com.prodpingu.fitnesstracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TrackerViewModel extends AndroidViewModel {
    private TrackerRepository repository;
    private LiveData<List<Tracker>> allEntries;

    public TrackerViewModel(@NonNull Application application) {
        super(application);

        repository = new TrackerRepository(application);
        allEntries = repository.getAllEntries();
    }

    public void insert(Tracker tracker) {
        repository.insert(tracker);
    }

    public void update(Tracker tracker) {
        repository.update(tracker);
    }

    public void delete(Tracker tracker) {
        repository.delete(tracker);
    }

    public LiveData<List<Tracker>> getAllEntries() {
        return allEntries;
    }
}
