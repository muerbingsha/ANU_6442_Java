package com.anu.sampledaocursor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.anu.sampledaocursor.R;
import com.anu.sampledaocursor.db.Event;
import com.anu.sampledaocursor.db.EventAttrib;
import com.anu.sampledaocursor.db.EventRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class EditorActivity extends AppCompatActivity {

    private int eventId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // set title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Event");




        // UI
        final EditText editTitle = findViewById(R.id.edit_event_title);
        final EditText editLocation = findViewById(R.id.edit_event_location);
        final Button editDate = findViewById(R.id.edit_event_date);
        final Button editTime = findViewById(R.id.edit_event_time);
        final Button editAlert = findViewById(R.id.edit_event_alert);
        final EditText editUrl = findViewById(R.id.edit_event_url);
        final EditText editNote = findViewById(R.id.edit_event_notes);




        // get data
        Intent go = getIntent();
        eventId = go.getIntExtra(EventAttrib.ID.toString(), -1);

        // old event
        if (eventId != -1) {

            // get event
            EventRepository er = new EventRepository(getApplication());
            Event selectedEvent = er.getEventById(eventId);

            final String eventTitle = selectedEvent.title;
            System.out.printf("Shark", eventTitle);
            String eventLocation = selectedEvent.location;
            String eventDate = selectedEvent.date;
            String eventTime = selectedEvent.time;
            String eventAlert = selectedEvent.alert;
            String eventUrl = selectedEvent.url;
            String eventNotes = selectedEvent.notes;
            boolean eventCompleted = selectedEvent.completed;

            // fill in data
            if (eventTitle != null) {
                editTitle.setText(eventTitle);
            }
            if (eventLocation != null) {
                editLocation.setText(eventLocation);
            }
            if (eventDate != null) {
                editDate.setText(eventDate);
            }
            if (editTime != null) {
                editTime.setText(eventTime);
            }
            if (eventAlert != null) {
                editAlert.setText(eventAlert);
            }
            if (eventUrl != null) {
                editUrl.setText(eventUrl);
            }
            if (eventNotes != null) {
                editNote.setText(eventNotes);
            }

        }





        final Calendar mCalendar = Calendar.getInstance();

        /**
         * select time
         */
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                //  Calendar mCalendar = Calendar.getInstance();
                // hour and minute are current time
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = mCalendar.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mCalendar.set(Calendar.MINUTE, selectedMinute);
                        editTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Calendar mCalendar = Calendar.getInstance();
                new DatePickerDialog(
                        EditorActivity. this, date ,
                        mCalendar .get(Calendar. YEAR ) ,
                        mCalendar .get(Calendar. MONTH ) ,
                        mCalendar .get(Calendar. DAY_OF_MONTH )
                ).show() ;

            }

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet (DatePicker view , int year , int monthOfYear , int dayOfMonth) {
                    mCalendar .set(Calendar. YEAR , year) ;
                    mCalendar .set(Calendar. MONTH , monthOfYear) ;
                    mCalendar .set(Calendar. DAY_OF_MONTH , dayOfMonth) ;
                    updateLabel() ;
                }
            } ;

            private void updateLabel () {
                String myFormat = "dd/MM/yy" ;
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ()) ;
                Date date = mCalendar.getTime();
                System.out.println("showing you date: "+ sdf.format(date.getTime()));
                editDate.setText(sdf.format(date)) ;

            }


        });


        /**
         * alert
         */
        editAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EditorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Do you want to set alarm")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println("i am in calender: "+ mCalendar.getTimeInMillis());
                                editAlert.setText("Alarm set");

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editAlert.setText("None");
                            }
                        })
                        .show();

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        EventRepository er = new EventRepository(getApplication());
        final EditText editTitle = findViewById(R.id.edit_event_title);
        final EditText editLocation = findViewById(R.id.edit_event_location);
        final EditText editUrl = findViewById(R.id.edit_event_url);
        final EditText editNote = findViewById(R.id.edit_event_notes);
        final Button editDate = findViewById(R.id.edit_event_date);
        final Button editTime = findViewById(R.id.edit_event_time);
        final Button editAlert = findViewById(R.id.edit_event_alert);

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_add) {
            // add or update

            Event newEvent = new Event(editTitle.getText().toString());
            newEvent.location = editLocation.getText().toString();
            newEvent.time = editTime.getText().toString();
            newEvent.date = editDate.getText().toString();
            newEvent.alert = editAlert.getText().toString();
            newEvent.url = editUrl.getText().toString();
            newEvent.notes = editNote.getText().toString();
            newEvent.completed = false;

            er.insertOneEvent(newEvent);

            startActivity(new Intent(EditorActivity.this, CursorActivity.class));
            finish();

        } else if (id == R.id.edit_update) {

            if (eventId != -1) {
                Event updatedEvent = er.getEventById(eventId);

                updatedEvent.title = editTitle.getText().toString();
                updatedEvent.location = editLocation.getText().toString();
                updatedEvent.time = editTime.getText().toString();
                updatedEvent.date = editDate.getText().toString();
                updatedEvent.alert = editAlert.getText().toString();
                updatedEvent.url = editUrl.getText().toString();
                updatedEvent.notes = editNote.getText().toString();
                updatedEvent.completed = false;
                er.updateOneEvent(updatedEvent);

            }


            startActivity(new Intent(EditorActivity.this, CursorActivity.class));
            finish();

        } else if (id == R.id.edit_back) {
            startActivity(new Intent(EditorActivity.this, CursorActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
