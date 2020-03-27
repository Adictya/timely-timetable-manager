package com.Adictya.timely.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Slots_table")
public class TimeSlots {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @NonNull
    @ColumnInfo(name = "slot_col")
    private String slot;

    @NonNull
    @ColumnInfo(name = "lab_col")
    private Integer slot_lab;

    @NonNull
    @ColumnInfo(name = "day_col")
    private Integer slot_day;

    @NonNull
    @ColumnInfo(name = "time_col")
    private String slot_time;

    @ColumnInfo(name = "course_col")
    private String slot_course;

    @ColumnInfo(name = "class_col")
    private String slot_class;

    public TimeSlots(@NonNull String slot,@NonNull Integer slot_lab, @NonNull Integer slot_day, @NonNull String slot_time, String slot_course, String slot_class) {
        this.slot = slot;
        this.slot_lab = slot_lab;
        this.slot_day = slot_day;
        this.slot_time = slot_time;
        this.slot_course = slot_course;
        this.slot_class = slot_class;
    }

    @NonNull
    public Integer getSlot_lab() {
        return slot_lab;
    }

    public void setSlot_lab(@NonNull Integer slot_lab) {
        this.slot_lab = slot_lab;
    }

    public String getSlot_class() {
        return slot_class;
    }

    public void setSlot_class(String slot_class) {
        this.slot_class = slot_class;
    }

    public String getSlot_course() {
        return slot_course;
    }

    public void setSlot_course(String slot_course) {
        this.slot_course = slot_course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(@NonNull String slot) {
        this.slot = slot;
    }

    public Integer getSlot_day() {
        return slot_day;
    }

    public void setSlot_day(@NonNull Integer slot_day) {
        this.slot_day = slot_day;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(@NonNull String slot_time) {
        this.slot_time = slot_time;
    }
}
