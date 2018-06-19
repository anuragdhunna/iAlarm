package com.anuragdhunna.www.ialarm.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.anuragdhunna.www.ialarm.R;
import com.anuragdhunna.www.ialarm.activities.MainActivity;
import com.anuragdhunna.www.ialarm.entities.Alarm;
import com.anuragdhunna.www.ialarm.fragments.AlarmDetailFragment;

import java.util.List;

/**
 * Created by anuragdhunna on 17/5/18.
 */

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmRecyclerAdapter.AlarmViewHolder> {

    private final LayoutInflater mInflater;
    private List<Alarm> mAlarms; // Cached copy of alarms

    public AlarmRecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.alarm_list_layout, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AlarmViewHolder holder, int position) {

        if (mAlarms != null) {
            Alarm alarm = mAlarms.get(position);
            holder.alarmListItemRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Show Details of Alarm

                    MainActivity mainActivity = (MainActivity)holder.itemView.getContext();
                    FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                    Fragment fragment = AlarmDetailFragment.newInstance(null, null);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
//                            .addToBackStack(AlarmDetailFragment.class.getName())
                            .commit();
                }
            });
            holder.alarmItemView.setText(alarm.getName());
            holder.dateTimeTV.setText(alarm.getDateTime());
            if (alarm.getStatus().equals("Y")) {
                holder.statusSwitch.setChecked(true);
            } else {
                holder.statusSwitch.setChecked(false);
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.alarmItemView.setText("No Alarm");
        }
    }

    public void setAlarms(List<Alarm> alarms) {
        mAlarms = alarms;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAlarms != null)
            return mAlarms.size();
        else return 0;
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        private final TextView alarmItemView;
        private final TextView dateTimeTV;
        private final Switch statusSwitch;
        private final RelativeLayout alarmListItemRL;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            alarmItemView = itemView.findViewById(R.id.alarmNameTV);
            dateTimeTV = itemView.findViewById(R.id.dateTimeTV);
            statusSwitch = itemView.findViewById(R.id.statusSwitch);
            alarmListItemRL = itemView.findViewById(R.id.alarmListItemRL);
        }
    }
}
