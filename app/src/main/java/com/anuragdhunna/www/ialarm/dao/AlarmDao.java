package com.anuragdhunna.www.ialarm.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.anuragdhunna.www.ialarm.entities.AlarmEntity;

import java.util.List;

/**
 * Created by anuragdhunna on 16/5/18.
 */

@Dao
public interface AlarmDao {

    @Insert
    void insert(AlarmEntity alarmEntity);

    @Query("DELETE FROM alarm")
    void deleteAll();

    @Query("SELECT * from alarm")
    LiveData<List<AlarmEntity>> getAllAlarm();

    @Query("SELECT * FROM alarm WHERE alarm_id = :alarmId")
    LiveData<AlarmEntity> getAlarmDetails(int alarmId);

}
