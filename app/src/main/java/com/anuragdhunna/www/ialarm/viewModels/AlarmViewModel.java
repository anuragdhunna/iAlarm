package com.anuragdhunna.www.ialarm.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.anuragdhunna.www.ialarm.entities.AlarmEntity;
import com.anuragdhunna.www.ialarm.entities.AlarmRepo;

import java.util.List;

/**
 * Created by anuragdhunna on 17/5/18.
 */

public class AlarmViewModel extends AndroidViewModel {

    private AlarmRepo mRepository;
    private LiveData<List<AlarmEntity>> mAllAlarms;
    private LiveData<AlarmEntity> malramDetails;
    private int mAlarmId;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AlarmRepo(application);
        mAllAlarms = mRepository.getAllAlarms();
    }

    public AlarmViewModel(@NonNull Application application, int alarmId) {
        super(application);
        mAlarmId = alarmId;
        mRepository = new AlarmRepo(application, mAlarmId);
        mAllAlarms = mRepository.getAllAlarms();

        malramDetails = mRepository.getAlarmDetails();

    }

    public LiveData<List<AlarmEntity>> getAllAlarms() {
        return mAllAlarms;
    }

    public void insert(AlarmEntity alarmEntity) {
        mRepository.insert(alarmEntity);
    }

    public LiveData<AlarmEntity> getAlarm() {
        return malramDetails;
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mAlarmId;


        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mAlarmId = productId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new AlarmViewModel(mApplication, mAlarmId);
        }
    }

}
