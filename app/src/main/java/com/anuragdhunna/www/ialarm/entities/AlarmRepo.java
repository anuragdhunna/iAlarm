package com.anuragdhunna.www.ialarm.entities;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.anuragdhunna.www.ialarm.dao.AlarmDao;

import java.util.List;

/**
 * Created by anuragdhunna on 16/5/18.
 */

public class AlarmRepo {

    private AlarmDao mAlarmDao;
    private LiveData<List<Alarm>> mAllAlarms;

    public AlarmRepo(Application application) {
        AlarmRDB db = AlarmRDB.getDatabase(application);
        mAlarmDao = db.alarmDao();
        mAllAlarms = mAlarmDao.getAllAlarm();
    }

    public LiveData<List<Alarm>> getAllAlarms() {
        return mAllAlarms;
    }

    public void insert (Alarm alarm) {
        new insertAsyncTask(mAlarmDao).execute(alarm);
    }

    private static class insertAsyncTask extends AsyncTask<Alarm, Void, Void> {

        private AlarmDao mAsyncTaskDao;

        insertAsyncTask(AlarmDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Alarm... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
