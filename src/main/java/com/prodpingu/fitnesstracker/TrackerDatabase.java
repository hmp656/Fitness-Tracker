package com.prodpingu.fitnesstracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Tracker.class, version = 1, exportSchema = false   )
public abstract class TrackerDatabase extends RoomDatabase {
    private static TrackerDatabase instance;
    public abstract TrackerDao trackerDao();

    public static synchronized TrackerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TrackerDatabase.class, "tracker_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private TrackerDao trackerDao;
        private PopulateDBAsyncTask(TrackerDatabase db) {
            trackerDao = db.trackerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            trackerDao.insert(new Tracker(64, 190, 0));
            return null;
        }
    }
}
