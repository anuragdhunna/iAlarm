package com.anuragdhunna.www.ialarm.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.anuragdhunna.www.ialarm.entities.Alarm;
import com.anuragdhunna.www.ialarm.entities.AlarmRepo;

import java.util.List;

/**
 * Created by anuragdhunna on 17/5/18.
 */

public class AlarmViewModel extends AndroidViewModel {

    private AlarmRepo mRepository;
    private LiveData<List<Alarm>> mAllAlarms;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AlarmRepo(application);
        mAllAlarms = mRepository.getAllAlarms();
    }

    public LiveData<List<Alarm>> getAllAlarms() {
        return mAllAlarms;
    }

    public void insert(Alarm alarm) {
        mRepository.insert(alarm);
    }

}
