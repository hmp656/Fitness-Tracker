package com.prodpingu.fitnesstracker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TrackerRepository {
    private TrackerDao trackerDao;
    private LiveData<List<Tracker>> allEntries;

    public TrackerRepository(Application application) {
        TrackerDatabase trackerDatabase = TrackerDatabase.getInstance(application);
        trackerDao = trackerDatabase.trackerDao();
        allEntries = trackerDao.getAllEntries();
    }

    public void insert(Tracker tracker){
        new InsertEntryAsyncTask(trackerDao).execute(tracker);
    }

    public void delete(Tracker tracker){
        new DeleteEntryAsyncTask(trackerDao).execute(tracker);
    }

    public void update(Tracker tracker){
        new UpdateEntryAsyncTask(trackerDao).execute(tracker);
    }

    public LiveData<List<Tracker>> getAllEntries() {
        return allEntries;
    }


    // Async
    private static class InsertEntryAsyncTask extends AsyncTask<Tracker, Void, Void> {
        private TrackerDao trackerDao;
        private InsertEntryAsyncTask(TrackerDao trackerDao) {
            this.trackerDao = trackerDao;
        }

        @Override
        protected Void doInBackground(Tracker... trackers) {
            trackerDao.insert(trackers[0]);
            return null;
        }
    }

    private static class UpdateEntryAsyncTask extends AsyncTask<Tracker, Void, Void> {
        private TrackerDao trackerDao;
        private UpdateEntryAsyncTask(TrackerDao trackerDao) {
            this.trackerDao = trackerDao;
        }

        @Override
        protected Void doInBackground(Tracker... trackers) {
            trackerDao.update(trackers[0]);
            return null;
        }
    }
    private static class DeleteEntryAsyncTask extends AsyncTask<Tracker, Void, Void> {
        private TrackerDao trackerDao;
        private DeleteEntryAsyncTask(TrackerDao trackerDao) {
            this.trackerDao = trackerDao;
        }

        @Override
        protected Void doInBackground(Tracker... trackers) {
            trackerDao.delete(trackers[0]);
            return null;
        }
    }
}
