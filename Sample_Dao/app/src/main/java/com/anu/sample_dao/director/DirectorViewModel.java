/**
 * Reference: https://github.com/lomza/movies-room
 */


package com.anu.sample_dao.director;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anu.sample_dao.db.Director;
import com.anu.sample_dao.db.DirectorDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DirectorViewModel extends AndroidViewModel {
    private DirectorDao directorDao;
    private LiveData<List<Director>> directorLiveData;


    /**
     * need super
     * @param application
     */
    public DirectorViewModel(@NotNull Application application) {
        super(application);
//        directorDao = MovieDatabase.getDatabase(application).directorDao;

    }

    public void insert(Director... directors) {

    }

    public void update(Director directors) {

    }

}
