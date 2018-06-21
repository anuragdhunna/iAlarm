package com.anuragdhunna.www.ialarm.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.amitshekhar.DebugDB;
import com.anuragdhunna.www.ialarm.R;
import com.anuragdhunna.www.ialarm.fragments.AlarmListFragment;
import com.anuragdhunna.www.ialarm.viewModels.AlarmViewModel;

public class MainActivity extends AppCompatActivity {

    private AlarmViewModel mAlarmViewModel;
    ImageButton allAlarmIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DebugDB.getAddressLog();


        Log.e("===============", DebugDB.getAddressLog());
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = AlarmListFragment.newInstance(null, null);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

//        allAlarmIB = findViewById(R.id.allAlramIB);
//        allAlarmIB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewAlarmActivity.class);
//                startActivityForResult(intent, NEW_ALARM_ACTIVITY_REQUEST_CODE);
//            }
//        });
//        RecyclerView recyclerView = findViewById(R.id.alarmsRV);
//        final AlarmRecyclerAdapter adapter = new AlarmRecyclerAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mAlarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
//
//        mAlarmViewModel.getAllAlarms().observe(this, new Observer<List<AlarmEntity>>() {
//            @Override
//            public void onChanged(@Nullable final List<AlarmEntity> alarms) {
//                // Update the cached copy of the alarms in the adapter.
//                adapter.setAlarms(alarms);
//            }
//        });
    }

//    public static final int NEW_ALARM_ACTIVITY_REQUEST_CODE = 1;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_ALARM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            AlarmEntity alarm = new AlarmEntity(data.getStringExtra(NewAlarmActivity.EXTRA_REPLY),  "2108-05-27 20:20:00:000", "Y", "Y", "Y");
//            mAlarmViewModel.insert(alarm);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
//    }
}