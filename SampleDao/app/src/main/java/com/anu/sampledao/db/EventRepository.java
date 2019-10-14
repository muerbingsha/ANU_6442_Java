package com.anu.sampledao.db;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class EventRepository {

    private EventDao eventDao;
    private List<Event> allEvents;
    private List<Event> completedEvents;
    private List<Event> incompletedEvents;


    public EventRepository(Application app) {
        EventDatabase  database = EventDatabase.getDatabase(app);
        eventDao = database.eventDao();
        allEvents = eventDao.getAllEvents();
    }

    public void insertOneEvent(Event event) {
        new insertAsyncTask(eventDao).execute(event);
    }

    public void updateOneEvent(Event event) {
        new updateAsyncTask(eventDao).execute(event);
    }

    public void deleteOneEvent(Event event) {
        new deleteAsyncTask(eventDao).execute(event); // async thread
//        eventDao.deleteOneEvent(event); // main thread
    }


    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    public Event getEventByTitle(String title) {
        return eventDao.getEventByTitle(title);
    }





    // synchronically because dao doesn't allow executing in main thread
    // 3rd Void: return type
    private static class insertAsyncTask extends android.os.AsyncTask<Event, Void, Void> {

        // pass this to database manipulation
        private EventDao eventDao;

        insertAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            this.eventDao.insertOneEvent(events[0]);

            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Event, Void, Void> {

        // pass this to database manipulation
        private EventDao eventDao;

        updateAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            this.eventDao.updateOneEvent(events[0]);

            return null;
        }
    }



    private static class deleteAsyncTask extends AsyncTask<Event, Void, Void> {

        // pass this to database manipulation
        private EventDao eventDao;

        deleteAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            this.eventDao.deleteOneEvent(events[0]);

            return null;
        }
    }

}
