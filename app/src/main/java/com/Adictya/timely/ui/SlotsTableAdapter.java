package com.Adictya.timely.ui;

import android.content.Context;
import android.util.Log;
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

public class SlotsTableAdapter extends RecyclerView.Adapter<SlotsTableAdapter.SlotsTableViewHolder>{

    private final LayoutInflater slotViewInflater;

    private List<TimeSlots> timeSlotsList;

    public SlotsTableAdapter(Context context) {
        slotViewInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public SlotsTableAdapter.SlotsTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = slotViewInflater.inflate(R.layout.slot_element, parent,false);
        SlotsTableAdapter.SlotsTableViewHolder tsm = new SlotsTableAdapter.SlotsTableViewHolder(view);
        return tsm;
    }

    @Override
    public void onBindViewHolder(@NonNull SlotsTableAdapter.SlotsTableViewHolder holder, int position) {
        if (timeSlotsList != null) {
            TimeSlots current = timeSlotsList.get(position);
            holder.slotViewCourse.setText(current.getSlot_course());
            holder.slotViewClass.setText(current.getSlot_class());
            holder.slotViewSlot.setText(current.getSlot());
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

    public class SlotsTableViewHolder extends RecyclerView.ViewHolder{

        private TextView slotViewSlot;
        private TextView slotViewClass;
        private TextView slotViewCourse;

        public SlotsTableViewHolder(@NonNull View itemView) {
            super(itemView);
            slotViewSlot = itemView.findViewById(R.id.slot_slot);
            slotViewClass = itemView.findViewById(R.id.slot_class);
            slotViewCourse = itemView.findViewById(R.id.slot_coursename);
        }
    }
}
