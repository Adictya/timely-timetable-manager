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
            Classes classes = new Classes("CSE1002","Maths","mths");
            TimeSlots timeSlots = new TimeSlots("A1",0,0,"9:00 AM","Internet of Things","SJT-725");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("A2",0,1,"10:30 AM","Software Engineering","SJT-524");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("A2",1,2,"11:30 AM","Software Engineering","SJT-524");
            timeSlotsDAO.insert(timeSlots);
            timeSlots = new TimeSlots("B1",1,6,"9:00 AM",null,null);
            timeSlotsDAO.insert(timeSlots);

            return null;
        }
    }
}
