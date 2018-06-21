package com.anuragdhunna.www.ialarm.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anuragdhunna.www.ialarm.R;
import com.anuragdhunna.www.ialarm.entities.AlarmEntity;
import com.anuragdhunna.www.ialarm.viewModels.AlarmViewModel;

public class AlarmDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ALARM_ID = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int alarmId;
    private String mParam2;

    public AlarmDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param alarmId Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmDetailFragment newInstance(int alarmId, String param2) {
        AlarmDetailFragment fragment = new AlarmDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ALARM_ID, alarmId);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alarmId = getArguments().getInt(ALARM_ID);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText editAlarmET;
    TimePicker dateTimeTP;
    Switch snoozeS;
    Switch statusS;
    Switch repeatS;

    private AlarmViewModel mAlarmViewModel;
    View view;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm_detail, container, false);

        Log.e("=========alarmId=====", ""+ alarmId);

        AlarmViewModel.Factory factory = new AlarmViewModel.Factory(
                getActivity().getApplication(), alarmId);
        mAlarmViewModel = ViewModelProviders.of(this, factory).get(AlarmViewModel.class);


        editAlarmET = view.findViewById(R.id.editAlarmET);
        dateTimeTP = view.findViewById(R.id.dateTimeTP);
        snoozeS = view.findViewById(R.id.snoozeS);
        statusS = view.findViewById(R.id.statusS);
        repeatS = view.findViewById(R.id.repeatS);

        mAlarmViewModel.getAlarm().observe(this, new Observer<AlarmEntity>(){

            @Override
            public void onChanged(@Nullable AlarmEntity alarmEntity) {
//                mAlarmViewModel.getAlarm(alarmId)
//                AlarmEntity alarmEntity = mAlarmViewModel.getAlarm(alarmId);
                Log.e("=====alarmEntity=====", ""+ alarmEntity);

                if (alarmEntity != null) {
                    editAlarmET.setText(alarmEntity.getName());
                    dateTimeTP.setHour(alarmEntity.getHour());
                    dateTimeTP.setMinute(alarmEntity.getMinute());

                    String snooze = alarmEntity.getSnooze();
                    Log.e("=========snooze=====", ""+ snooze);
                    if (snooze.equalsIgnoreCase("Y")) {
                        snoozeS.setChecked(true);
                    }

                    String status = alarmEntity.getStatus();
                    if (status.equalsIgnoreCase("Y")) {
                        statusS.setChecked(true);
                    }

                    String repeat = alarmEntity.getRepeat();
                    if (repeat.equalsIgnoreCase("Y")) {
                        repeatS.setChecked(true);
                    }
                } else {
                    Toast.makeText(getContext(), "AlarmEntity is Null", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
