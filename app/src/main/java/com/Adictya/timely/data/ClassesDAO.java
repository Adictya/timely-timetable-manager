package com.Adictya.timely.data;

import com.Adictya.timely.model.Classes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ClassesDAO {

    @Insert
    void insert(Classes classes);

    @Query("DELETE FROM classes_table")
    void deleteAll();

    @Query("DELETE FROM classes_table where ID = :id")
    int deleteAClasses(int id);

    @Query("UPDATE classes_table SET code_col = :courseCode , name_col = :courseName , alias_col = :courseAlias WHERE id = :id")
    int updateClasses(int id,String courseCode, String courseName, String courseAlias);

    @Query("SELECT * FROM classes_table")
    LiveData<List<Classes>> getAllClasses();
}
