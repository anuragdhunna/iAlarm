package com.anuragdhunna.www.ialarm.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by anuragdhunna on 16/5/18.
 */

@Entity(tableName = "alarm")
public class Alarm {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "alarm_id")
    private int alarmId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "date_time")
    private String dateTime;

    @NonNull
    @ColumnInfo(name = "snooze")
    private String snooze; // Y/N

    @NonNull
    @ColumnInfo(name = "status")
    private String status; // Y/N

    @NonNull
    @ColumnInfo(name = "repeat")
    private String repeat;  // Y/N

    public Alarm(@NonNull String name, String dateTime, String snooze, String status, String repeat ) {
        this.name = name;
        this.dateTime = dateTime;
        this.snooze = snooze;
        this.status = status;
        this.repeat = repeat;
    }

//    public Alarm(@NonNull String name) {
//        this.name = name;
//    }

    @NonNull
    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(@NonNull int alarmId) {
        this.alarmId = alarmId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(@NonNull String dateTime) {
        this.dateTime = dateTime;
    }

    @NonNull
    public String getSnooze() {
        return snooze;
    }

    public void setSnooze(@NonNull String snooze) {
        this.snooze = snooze;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    @NonNull
    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(@NonNull String repeat) {
        this.repeat = repeat;
    }
}
