package com.anu.sample_dao.db;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Movie.class, Director.class},
        version = 1)
public abstract class MoviesDatabase extends RoomDatabase {


    private static MoviesDatabase INSTANCE;
    private static final String DB_NAME = "movies.db";

    public abstract MovieDao movieDao();
    public abstract DirectorDao directorDao();


//    @NonNull
//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//
//    @NonNull
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }
//
//
//    @Override
//    public void clearAllTables() {
//
//    }

    public static MoviesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            // only one thread to manipulate data
//            synchronized (this) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, DB_NAME)
                            // SHOULD NOT BE USED IN PRODUCTION !!!
                            // Room doesn't support database access on the main thread
                            // unless you've called allowMainThreadQueries() on the builder
                            // because it might lock the UI for a long period of time
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MoviesDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public void clearDb() {
        if (INSTANCE != null) {
            new PopulateDbAsync(INSTANCE).execute();
        }
    }


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final MovieDao movieDao;
        private final DirectorDao directorDao;

        public PopulateDbAsync(MoviesDatabase instance) {
            movieDao = instance.movieDao();
            directorDao = instance.directorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAll();
            directorDao.deleteAll();

            Director directorOne = new Director("Adam McKay");
            Director directorTwo = new Director("Denis Villeneuve");
            Director directorThree = new Director("Morten Tyldum");

            Movie movieOne = new Movie("The Big Short", (int) directorDao.insert(directorOne));
            final int dIdTwo = (int) directorDao.insert(directorTwo);
            Movie movieTwo = new Movie("Arrival", dIdTwo);
            Movie movieThree = new Movie("Blade Runner 2049", dIdTwo);
            Movie movieFour = new Movie("Passengers", (int) directorDao.insert(directorThree));

            movieDao.insert(movieOne, movieTwo, movieThree, movieFour);

            return null;
        }
    }
}
