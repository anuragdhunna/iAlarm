package com.anuragdhunna.www.ialarm.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.anuragdhunna.www.ialarm.R;
import com.anuragdhunna.www.ialarm.activities.NewAlarmActivity;
import com.anuragdhunna.www.ialarm.adapters.AlarmRecyclerAdapter;
import com.anuragdhunna.www.ialarm.entities.AlarmEntity;
import com.anuragdhunna.www.ialarm.viewModels.AlarmViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AlarmListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AlarmListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmListFragment newInstance(String param1, String param2) {
        AlarmListFragment fragment = new AlarmListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private AlarmViewModel mAlarmViewModel;
    ImageButton allAlarmIB;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        allAlarmIB = view.findViewById(R.id.allAlramIB);
        allAlarmIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewAlarmActivity.class);
                startActivityForResult(intent, NEW_ALARM_ACTIVITY_REQUEST_CODE);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.alarmsRV);
        final AlarmRecyclerAdapter adapter = new AlarmRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAlarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);

        mAlarmViewModel.getAllAlarms().observe(this, new Observer<List<AlarmEntity>>() {
            @Override
            public void onChanged(@Nullable final List<AlarmEntity> alarmEntities) {
                // Update the cached copy of the alarmEntities in the adapter.
                adapter.setAlarms(alarmEntities);
            }
        });

        return view;
    }

    public static final int NEW_ALARM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ALARM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            AlarmEntity alarmEntity = new AlarmEntity(data.getStringExtra(NewAlarmActivity.ALARM_NAME),
                    data.getIntExtra(NewAlarmActivity.ALARM_HOUR, 0),
                    data.getIntExtra(NewAlarmActivity.ALARM_MINUTE, 0),
                    data.getStringExtra(NewAlarmActivity.ALARM_SNOOZE),
                    data.getStringExtra(NewAlarmActivity.ALARM_STATUS),
                    data.getStringExtra(NewAlarmActivity.ALARM_REPEAT));
            mAlarmViewModel.insert(alarmEntity);
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


}
