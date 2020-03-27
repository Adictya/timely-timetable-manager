package com.Adictya.timely.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "classes_table")
public class Classes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "code_col")
    private String course_code;

    @NonNull
    @ColumnInfo(name = "name_col")
    private String course_name;

    @ColumnInfo(name = "alias_col")
    private String course_alias;

    public Classes(@NonNull String course_code, @NonNull String course_name, String course_alias) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.course_alias = course_alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(@NonNull String course_code) {
        this.course_code = course_code;
    }

    @NonNull
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(@NonNull String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_alias() {
        return course_alias;
    }

    public void setCourse_alias(String course_alias) {
        this.course_alias = course_alias;
    }
}
