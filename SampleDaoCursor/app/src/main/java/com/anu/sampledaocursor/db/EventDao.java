package com.anu.sampledaocursor.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface EventDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOneEvent(Event newEvent);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateOneEvent(Event event);


    @Delete
    void deleteOneEvent(Event event);


    @Query("SELECT * FROM event")
    List<Event> getAllEvents();

    @Query("SELECT * FROM event")
    Cursor getAllEventsCursor();


    @Query("SELECT * FROM event where _id=:id")
    Event getEventById(int id);


}




