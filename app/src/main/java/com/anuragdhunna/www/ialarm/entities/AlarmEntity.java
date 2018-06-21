package com.anuragdhunna.www.ialarm.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by anuragdhunna on 16/5/18.
 */

@Entity(tableName = "alarm")
public class AlarmEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "alarm_id")
    private int alarmId;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "hour")
    private int hour;

    @NonNull
    @ColumnInfo(name = "minute")
    private int minute;

    @NonNull
    @ColumnInfo(name = "snooze")
    private String snooze; // Y/N

    @NonNull
    @ColumnInfo(name = "status")
    private String status; // Y/N

    @NonNull
    @ColumnInfo(name = "repeat")
    private String repeat;  // Y/N

    public AlarmEntity(@NonNull String name, int hour, int minute, String snooze, String status, String repeat ) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.snooze = snooze;
        this.status = status;
        this.repeat = repeat;
    }

//    public AlarmEntity(@NonNull String name) {
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
    public int getHour() {
        return hour;
    }

    public void setHour(@NonNull int hour) {
        this.hour = hour;
    }

    @NonNull
    public int getMinute() {
        return minute;
    }

    public void setMinute(@NonNull int minute) {
        this.minute = minute;
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
