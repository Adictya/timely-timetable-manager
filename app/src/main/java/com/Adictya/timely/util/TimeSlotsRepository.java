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
    private LiveData<List<TimeSlots>> allTimeSlots;
    private LiveData<List<TimeSlots>> allSlots;
    private LiveData<List<TimeSlots>> allDaySlots;

    public TimeSlotsRepository(Application application) {
        TimeSlotsRoomDatabase db = TimeSlotsRoomDatabase.getDatabase(application);
        classesDAO = db.classesDAO();
        timeSlotsDAO = db.timeSlotsDAO();
        allClasses = classesDAO.getAllClasses();
        allTimeSlots = timeSlotsDAO.getAllTimeSlots();
        allSlots = timeSlotsDAO.getAllSlots();
    }

    public TimeSlotsRepository(Application application,Integer day){
        TimeSlotsRoomDatabase db = TimeSlotsRoomDatabase.getDatabase(application);
        timeSlotsDAO = db.timeSlotsDAO();
        allDaySlots = timeSlotsDAO.getAllDaySlots(day);
    }

    public LiveData<List<Classes>> getAllClasses(){
        return allClasses;
    }

    public LiveData<List<TimeSlots>> getAllTimeSlots(){
        return allTimeSlots;
    }

    public LiveData<List<TimeSlots>> getAllSlots(){
        return allSlots;
    }

    public LiveData<List<TimeSlots>> getAllDaySlots(){
        return allDaySlots;
    }

    private static class TimeSlotContainer {
        String course;
        String slot;
        String sclass;

        TimeSlotContainer(String course, String slot, String sclass){
            course = this.course;
            slot = this.slot;
            sclass = this.sclass;
        }
    }

    public void insertTimeSlot(TimeSlots timeSlots){
        TimeSlotContainer timeSlotContainer = new TimeSlotContainer(timeSlots.getSlot_course(),timeSlots.getSlot(),timeSlots.getSlot_class());
        new insertTimeSlotAsyncTask(timeSlotsDAO).execute(timeSlotContainer);
    }

    private class insertTimeSlotAsyncTask extends AsyncTask<TimeSlotContainer,Void,Void> {
        private TimeSlotsDAO asyncTaskDao;
        public insertTimeSlotAsyncTask(TimeSlotsDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(TimeSlotContainer... params) {
            asyncTaskDao.updateTimeSlots(params[0].slot,params[0].course,params[0].sclass);
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
