package com.Adictya.timely.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Adictya.timely.R;
import com.Adictya.timely.model.TimeSlots;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlotsTableAdapter extends RecyclerView.Adapter<SlotsTableAdapter.SlotsTableViewHolder>{

    private final LayoutInflater slotViewInflater;

    private List<TimeSlots> timeSlotsList;

//    private Context context;

    public SlotsTableAdapter(Context context) {
        slotViewInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public SlotsTableAdapter.SlotsTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == R.layout.slot_element){
            View view = slotViewInflater.inflate(R.layout.slot_element, parent,false);
        }

        else {
            View view = slotViewInflater.inflate(R.layout.add_button, parent,false);
        }
        View view = slotViewInflater.inflate(R.layout.slot_element, parent,false);
        SlotsTableAdapter.SlotsTableViewHolder tsm = new SlotsTableAdapter.SlotsTableViewHolder(view);
//        context = parent.getContext();
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

    public int getItemViewType(int position) {
        return (position == timeSlotsList.size()) ? R.layout.add_button : R.layout.slot_element;
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
        public Button button;


        public SlotsTableViewHolder(@NonNull View itemView) {
            super(itemView);
            slotViewSlot = itemView.findViewById(R.id.slot_slot);
            slotViewClass = itemView.findViewById(R.id.slot_class);
            slotViewCourse = itemView.findViewById(R.id.slot_coursename);
            button = itemView.findViewById(R.id.add_button);
        }
    }
}
