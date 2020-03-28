package com.Adictya.timely.util;

import android.app.Application;
import android.os.AsyncTask;

import com.Adictya.timely.data.ClassesDAO;
import com.Adictya.timely.data.TimeSlotsDAO;
import com.Adictya.timely.data.TimeSlotsRoomDatabase;
import com.Adictya.timely.model.Classes;
import com.Adictya.timely.model.TimeSlots;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TimeSlotsRepository {
    private ClassesDAO classesDAO;
    private TimeSlotsDAO timeSlotsDAO;
    private LiveData<List<Classes>> allClasses;
    private LiveData<List<TimeSlots>> allTheorySlots;
    private LiveData<List<TimeSlots>> allLabSlots;
    private LiveData<List<TimeSlots>> allDaySlots;

    public TimeSlotsRepository(Application application) {
        TimeSlotsRoomDatabase db = TimeSlotsRoomDatabase.getDatabase(application);
        classesDAO = db.classesDAO();
        timeSlotsDAO = db.timeSlotsDAO();
        allClasses = classesDAO.getAllClasses();
        allTheorySlots = timeSlotsDAO.getAllTheorySlots();
        allLabSlots = timeSlotsDAO.getAllLabSlots();
    }


    public TimeSlotsRepository(Application application,Integer day){
        TimeSlotsRoomDatabase db = TimeSlotsRoomDatabase.getDatabase(application);
        timeSlotsDAO = db.timeSlotsDAO();
        allDaySlots = timeSlotsDAO.getAllDaySlots(day);
    }

    public LiveData<List<Classes>> getAllClasses(){
        return allClasses;
    }

    public LiveData<List<TimeSlots>> getAllTheorySlots(){
        return allTheorySlots;
    }

    public LiveData<List<TimeSlots>> getAllLabSlots(){
        return allLabSlots;
    }

    public LiveData<List<TimeSlots>> getAllDaySlots(){
        return allDaySlots;
    }

    private static class TimeSlotContainer {
        String course;
        String slot;
        String sclass;
        Integer slab;

        TimeSlotContainer(String course, String slot, String sclass, Integer slab){
            this.course = course;
            this.slot = slot;
            this.sclass = sclass;
            this.slab = slab;
        }
    }

    public void insertTimeSlot(TimeSlots timeSlots){
        TimeSlotContainer timeSlotContainer = new TimeSlotContainer(timeSlots.getSlot_course(),timeSlots.getSlot(),timeSlots.getSlot_class(),timeSlots.getSlot_lab());
        new insertTimeSlotAsyncTask(timeSlotsDAO).execute(timeSlotContainer);
    }

    private class insertTimeSlotAsyncTask extends AsyncTask<TimeSlotContainer,Void,Void> {
        private TimeSlotsDAO asyncTaskDao;
        public insertTimeSlotAsyncTask(TimeSlotsDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(TimeSlotContainer... params) {
            asyncTaskDao.updateTimeSlots(params[0].slot,params[0].course,params[0].sclass,params[0].slab);
            return null;
        }
    }

    public void deleteTimeSlot(TimeSlots timeSlots){
        new deleteTimeSlotAsyncTask(timeSlotsDAO).execute(timeSlots);
    }

    private class deleteTimeSlotAsyncTask extends AsyncTask<TimeSlots,Void,Void> {
        private TimeSlotsDAO asyncTaskDao;
        public deleteTimeSlotAsyncTask(TimeSlotsDAO timeSlotsDAO) {
            asyncTaskDao = timeSlotsDAO;
        }

        @Override
        protected Void doInBackground(TimeSlots... timeSlots) {
            asyncTaskDao.deleteATimeSlot(timeSlots[0].getSlot());
            return null;
        }
    }
}
