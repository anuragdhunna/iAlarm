package com.anuragdhunna.www.ialarm.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import com.anuragdhunna.www.ialarm.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author anuragdhunna
 */
public class NewAlarmActivity extends AppCompatActivity {

    public static final String ALARM_NAME = "alarm_name";
    public static final String ALARM_SNOOZE = "alarm_snooze";
    public static final String ALARM_STATUS = "alarm_status";
    public static final String ALARM_REPEAT = "alarm_repeat";
    public static final String ALARM_HOUR = "alarm_hour";
    public static final String ALARM_MINUTE = "alarm_minute";

    private EditText editAlarmET;
    TimePicker dateTimeTP;
    Switch snoozeS;
    Switch statusS;
    Switch repeatS;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        editAlarmET = findViewById(R.id.editAlarmET);
        dateTimeTP = findViewById(R.id.dateTimeTP);
        snoozeS = findViewById(R.id.snoozeS);
        statusS = findViewById(R.id.statusS);
        repeatS = findViewById(R.id.repeatS);

        final Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editAlarmET.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String alarm = editAlarmET.getText().toString();
                    replyIntent.putExtra(ALARM_NAME, alarm);

                    boolean snoozeChecked = snoozeS.isChecked();
                    String snooze = "N";
                    if (snoozeChecked) {
                        snooze = "Y";
                    }

                    Log.e("=========snooze=====", ""+ snooze);
                    boolean statusChecked = statusS.isChecked();
                    String status = "N";
                    if (statusChecked) {
                        status = "Y";
                    }
                    Log.e("========status======", ""+ status);

                    boolean repeatChecked = repeatS.isChecked();
                    String repeat = "N";
                    if (repeatChecked) {
                        repeat = "Y";
                    }
                    Log.e("========repeat======", ""+ repeat);

                    replyIntent.putExtra(ALARM_SNOOZE, snooze);
                    replyIntent.putExtra(ALARM_STATUS, status);
                    replyIntent.putExtra(ALARM_REPEAT, repeat);
                    replyIntent.putExtra(ALARM_HOUR, dateTimeTP.getHour());
                    replyIntent.putExtra(ALARM_MINUTE, dateTimeTP.getMinute());

                    Log.e("====getHour== ", ""+ dateTimeTP.getHour());
                    Log.e("====getMinute== ", ""+ dateTimeTP.getMinute());

                    //        2108-05-27 20:20:00:000
//                            2018-40-19 9:31:00:000
//                    Date c = Calendar.getInstance().getTime();
//                    SimpleDateFormat dfaa = new SimpleDateFormat("yyyy-mm-dd");
//                    String currentDate = dfaa.format(c);
//                    Log.e("====currentDate==", ""+ currentDate);
//
//                    String dateTime = currentDate + " " + dateTimeTP.getHour() + ":" + dateTimeTP.getMinute() + ":00:000";
//                    Log.e("====dateTime==", ""+ dateTime);


                    setResult(RESULT_OK, replyIntent);
                }

                finish();
            }
        });

    }
}
