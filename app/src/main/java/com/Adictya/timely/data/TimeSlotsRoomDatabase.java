package com.Adictya.timely.data;

import android.content.Context;
import android.os.AsyncTask;

import com.Adictya.timely.model.Classes;
import com.Adictya.timely.model.TimeSlots;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Classes.class, TimeSlots.class}, version = 1, exportSchema = false)
public abstract class TimeSlotsRoomDatabase extends RoomDatabase {

    public static volatile TimeSlotsRoomDatabase INSTANCE;
    public abstract ClassesDAO classesDAO();
    public abstract TimeSlotsDAO timeSlotsDAO();

    public static TimeSlotsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TimeSlotsRoomDatabase.class){
                if (INSTANCE == null){
                    //create our db
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TimeSlotsRoomDatabase.class,"timeslots_db")
//                            .addCallback(roomDatabaseCallback).createFromAsset("database/database2.db").fallbackToDestructiveMigration().build();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TimeSlotsRoomDatabase.class,"timeslots_db")
                            .addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final TimeSlotsDAO timeSlotsDAO;
        private final ClassesDAO classesDAO;
        public PopulateDbAsync(TimeSlotsRoomDatabase db){
            timeSlotsDAO = db.timeSlotsDAO();
            classesDAO = db.classesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            timeSlotsDAO.deleteAll();
            classesDAO.deleteAll();
            //for testing
            Classes classes = new Classes("CSE1002","Microprocessor and Interfacing","Micro");
            classes = new Classes("CSE1003","Internet of Things","IOT");

            TimeSlots timeSlots = new TimeSlots("A1",0,0,"8:00 AM","Microprocessor and Interfacing","SJT-522");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("B1",0,1,"8:00 AM","English for Engineers","TT-305");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("B1",0,2,"9:00 AM","English for Engineers","TT-305");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("A1",0,2,"9:00 AM","Microprocessor and Interfacing","SJT-522");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("A2",0,1,"2:00 PM","Internet of Things","SJT-201");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("B2",0,2,"2:00 PM","Personal Finance","MGT-213");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("L31+L32",1,4,"2:00 PM","Python Practical Knowledge","SJT-302");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("L41+L42",1,5,"2:00 PM","Basic Electrical Engineering","TT-523");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("A2",0,3,"3:00 PM","Internet of Things","SJT-201");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("B2",0,4,"3:00 PM","Personal Finance","MGT-213");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("C1",0,4,"3:00 PM",null,null);
            timeSlotsDAO.insert(timeSlots);
            return null;
        }
    }
}
