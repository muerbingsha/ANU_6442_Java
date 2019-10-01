/**
 * https://github.com/lomza/movies-room
 */



package com.anu.sample_dao.db;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;



@Entity(tableName = "movie",
        foreignKeys = @ForeignKey(entity = Director.class,
            parentColumns = "did",       // attribute on out table
            childColumns = "directorId", // attribute on this table
            onDelete = ForeignKey.CASCADE),
        indices = {@Index("title"), @Index("directorId")}) // debug, use compile error
public class Movie {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mid")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "directorId")
    public int directorId;

    public Movie(@NotNull String title, int directorId){
        this.title = title;
        this.directorId = directorId;
    }
}
