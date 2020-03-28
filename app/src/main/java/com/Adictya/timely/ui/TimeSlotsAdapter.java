package com.Adictya.timely.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Adictya.timely.R;
import com.Adictya.timely.model.TimeSlots;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.TimeSlotsViewHolder> {
    private final LayoutInflater slotViewInflater;

    private List<TimeSlots> timeSlotsList;

    public TimeSlotsAdapter(Context context) {
        slotViewInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TimeSlotsAdapter.TimeSlotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = slotViewInflater.inflate(R.layout.time_element, parent,false);
        TimeSlotsViewHolder tsm = new TimeSlotsViewHolder(view);
        return tsm;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotsAdapter.TimeSlotsViewHolder holder, int position) {
        Calendar time = new GregorianCalendar();
        if (timeSlotsList != null) {
            TimeSlots current = timeSlotsList.get(position);
            if (current.getSlot_time() !=null) {
                holder.slotViewCourse.setText(current.getSlot_course());
                holder.slotViewClass.setText(current.getSlot_class());
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
                try {
                    time.setTime(formatter.parse(current.getSlot_time()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.slotViewTime.setText(String.valueOf(time.get(Calendar.HOUR)));
                if (time.get(Calendar.AM_PM) == 1)
                    holder.slotViewAmpm.setText("PM");
                else
                    holder.slotViewAmpm.setText("AM");
                if (time.get(Calendar.MINUTE) != 0) {
                    holder.slotViewMin.setText(String.valueOf(time.get(Calendar.MINUTE)));
                } else holder.slotViewMin.setText(null);
            }
        }
    }

    public void setTimeSlots(List<TimeSlots> slots) {
        timeSlotsList = slots;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (timeSlotsList != null)
            return timeSlotsList.size();
        else
            return 0;
    }

    public class TimeSlotsViewHolder extends RecyclerView.ViewHolder{

        private TextView slotViewTime;
        private TextView slotViewAmpm;
        private TextView slotViewClass;
        private TextView slotViewCourse;
        private TextView slotViewMin;

        public TimeSlotsViewHolder(@NonNull View itemView) {
            super(itemView);
            slotViewTime = itemView.findViewById(R.id.time_time);
            slotViewAmpm = itemView.findViewById(R.id.time_ampm);
            slotViewClass = itemView.findViewById(R.id.time_class);
            slotViewCourse = itemView.findViewById(R.id.time_coursename);
            slotViewMin = itemView.findViewById(R.id.time_min);
        }
    }
}
