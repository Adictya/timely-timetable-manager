package com.Adictya.timely.data;

import com.Adictya.timely.model.Classes;
import com.Adictya.timely.model.TimeSlots;

import java.sql.Time;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TimeSlotsDAO {
    @Insert
    void insert(TimeSlots timeSlots);

    @Query("DELETE FROM Slots_table")
    void deleteAll();

    @Query("DELETE FROM Slots_table where slot_col = :slot")
    int deleteATimeSlot(String slot);

    @Query("UPDATE Slots_table SET course_col = :slotCourse, slot_col = :sclass WHERE slot_col = :slot")
    int updateTimeSlots(String slot,String sclass, String slotCourse);

    @Query("SELECT * FROM Slots_table WHERE course_col not null")
    LiveData<List<TimeSlots>> getAllTimeSlots();

    @Query("SELECT * FROM Slots_table WHERE course_col not null GROUP BY slot_col")
    LiveData<List<TimeSlots>> getAllSlots();

    @Query("SELECT * FROM SLOTS_TABLE WHERE course_col not null and day_col =:day")
    LiveData<List<TimeSlots>> getAllDaySlots(Integer day);
}
