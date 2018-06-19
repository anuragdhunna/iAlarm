package com.anuragdhunna.www.ialarm.entities;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.anuragdhunna.www.ialarm.dao.AlarmDao;

/**
 * Created by anuragdhunna on 16/5/18.
 */

@Database(entities = {Alarm.class}, version = 1)
public abstract class AlarmRDB extends RoomDatabase {

    public abstract AlarmDao alarmDao();

    private static AlarmRDB INSTANCE;

    public static AlarmRDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlarmRDB.class) {
                if (INSTANCE == null) {
                    // Create database here

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlarmRDB.class, "alarm_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AlarmDao mDao;

        PopulateDbAsync(AlarmRDB db) {
            mDao = db.alarmDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();       // YYYY-MM-DD HH:MM:SS.SSS
            Alarm alarm = new Alarm("Alarm 1", "2108-05-27 20:20:00:000", "Y", "Y", "Y");
            mDao.insert(alarm);
            alarm = new Alarm("Alarm 2", "2108-05-28 03:20:00:000", "N", "Y", "N");
            mDao.insert(alarm);
            return null;
        }
    }
}
