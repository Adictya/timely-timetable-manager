package com.Adictya.timely.model;

import android.app.Application;

import com.Adictya.timely.util.TimeSlotsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TimeSlotsViewModel extends AndroidViewModel {
    private TimeSlotsRepository timeSlotsRepository;
    private LiveData<List<TimeSlots>> allSlots;
    private LiveData<List<TimeSlots>> allDaySlots;

    public TimeSlotsViewModel(@NonNull Application application) {
        super(application);
        timeSlotsRepository = new TimeSlotsRepository(application);
        allSlots = timeSlotsRepository.getAllLabSlots();
    }

    public TimeSlotsViewModel(@NonNull Application application, boolean lab)
    {
        super(application);
        timeSlotsRepository = new TimeSlotsRepository(application);
        if(lab)
            allSlots = timeSlotsRepository.getAllLabSlots();
        else allSlots= timeSlotsRepository.getAllTheorySlots();
    }

    public TimeSlotsViewModel(@NonNull Application application, Integer day) {
        super(application);
        timeSlotsRepository = new TimeSlotsRepository(application,day);
        allDaySlots = timeSlotsRepository.getAllDaySlots();
    }
    public LiveData<List<TimeSlots>> getAllDaySlots(){ return allDaySlots; }
    public LiveData<List<TimeSlots>> getAllSlots(){ return allSlots; }

    public void insertTimeSlots(TimeSlots timeSlots){
        timeSlotsRepository.insertTimeSlot(timeSlots);
    }
}
