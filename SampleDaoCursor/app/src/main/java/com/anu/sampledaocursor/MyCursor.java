package com.anu.sampledaocursor;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.anu.sampledaocursor.db.EventAttrib;


public class MyCursor extends CursorAdapter {


    public MyCursor (Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cursor_row, parent, false);
    }
    // The bindView method is used to bind all data to a given view // such as setting the text on a TextView.


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template

        // ui
        TextView id = (TextView) view.findViewById(R.id.event_id);
        TextView title = (TextView) view.findViewById(R.id.event_title);
        TextView location = (TextView) view.findViewById(R.id.event_location);
        TextView date = (TextView) view.findViewById(R.id.event_date);
        TextView time = (TextView) view.findViewById(R.id.event_time);
        TextView alert = (TextView) view.findViewById(R.id.event_alert);
        TextView url = (TextView) view.findViewById(R.id.event_url);
        TextView note = (TextView) view.findViewById(R.id.event_notes);


        // from database
        int e_id = cursor.getInt(cursor.getColumnIndexOrThrow(EventAttrib.ID.toString()));
        String e_title = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.TITLE.toString()));
        String e_location = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.LOCATION.toString()));
        String e_date = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.DATE.toString()));
        String e_time = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.TIME.toString()));
        String e_alert = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.ALERT.toString()));
        String e_url = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.URL.toString()));
        String e_notes = cursor.getString(cursor.getColumnIndexOrThrow(EventAttrib.NOTES.toString()));
        int e_completed = cursor.getInt(cursor.getColumnIndexOrThrow(EventAttrib.COMPLETED.toString()));


        // set content
        id.setText(Integer.toString(e_id));
        title.setText(e_title);
        location.setText(e_location);
        date.setText(e_date);
        time.setText(e_time);
        alert.setText(e_alert);
        url.setText(e_url);
        note.setText(e_notes);
        if (! e_alert.equals("")) {
            id.setTextColor(Color.BLUE);
        }
        if (e_completed == 0) {
            id.setBackgroundColor(Color.RED);
        } else {
            id.setBackgroundColor(Color.GREEN);
        }
    }
}

