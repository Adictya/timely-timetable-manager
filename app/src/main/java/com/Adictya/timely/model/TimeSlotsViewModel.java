package com.Adictya.timely.model;

import android.app.Application;

import com.Adictya.timely.util.TimeSlotsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TimeSlotsViewModel extends AndroidViewModel {
    private TimeSlotsRepository timeSlotsRepository;
    private LiveData<List<TimeSlots>> allTimeSlots;
    private LiveData<List<TimeSlots>> allSlots;

    public TimeSlotsViewModel(@NonNull Application application) {
        super(application);
        timeSlotsRepository = new TimeSlotsRepository(application);
        allTimeSlots = timeSlotsRepository.getAllTimeSlots();
        allSlots = timeSlotsRepository.getAllSlots();
    }

    public LiveData<List<TimeSlots>> getAllTimeSlots(){
        return allTimeSlots;
    }

    public LiveData<List<TimeSlots>> getAllSlots(){ return allSlots; }

    public void insertTimeSlots(TimeSlots timeSlots){
        timeSlotsRepository.insertTimeSlot(timeSlots);
    }
}
