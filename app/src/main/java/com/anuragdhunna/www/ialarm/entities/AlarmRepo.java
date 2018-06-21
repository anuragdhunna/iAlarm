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
    private LiveData<List<AlarmEntity>> mAllAlarms;
    private LiveData<AlarmEntity> mAlarm;
    private int mAlarmId;

    public AlarmRepo(Application application) {
        AlarmRDB db = AlarmRDB.getDatabase(application);
        mAlarmDao = db.alarmDao();
        mAllAlarms = mAlarmDao.getAllAlarm();
    }

    public AlarmRepo(Application application, int alarmId) {
        AlarmRDB db = AlarmRDB.getDatabase(application);
        mAlarmDao = db.alarmDao();
        mAllAlarms = mAlarmDao.getAllAlarm();
        mAlarmId = alarmId;
        mAlarm = mAlarmDao.getAlarmDetails(alarmId);
    }

    public LiveData<List<AlarmEntity>> getAllAlarms() {
        return mAllAlarms;
    }

    public void insert (AlarmEntity alarmEntity) {
        new insertAsyncTask(mAlarmDao).execute(alarmEntity);
    }

    public LiveData<AlarmEntity> getAlarmDetails() {
        mAlarm = mAlarmDao.getAlarmDetails(mAlarmId);
        return  mAlarm;
    }

    private static class insertAsyncTask extends AsyncTask<AlarmEntity, Void, Void> {

        private AlarmDao mAsyncTaskDao;

        insertAsyncTask(AlarmDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AlarmEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
