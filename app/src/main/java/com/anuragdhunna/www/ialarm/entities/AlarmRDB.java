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

@Database(entities = {AlarmEntity.class}, version = 1)
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
            AlarmEntity alarmEntity = new AlarmEntity("AlarmEntity 1", 10, 23,"Y", "Y", "Y");
            mDao.insert(alarmEntity);
            alarmEntity = new AlarmEntity("AlarmEntity 2", 9, 41,"N", "Y", "N");
            mDao.insert(alarmEntity);
            return null;
        }
    }
}
