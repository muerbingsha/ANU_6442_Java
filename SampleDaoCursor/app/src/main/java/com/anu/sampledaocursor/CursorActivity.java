package com.anu.sampledaocursor;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.anu.sampledaocursor.db.Event;
import com.anu.sampledaocursor.db.EventAttrib;
import com.anu.sampledaocursor.db.EventRepository;

import java.util.Objects;


public class CursorActivity extends AppCompatActivity {

    private Cursor myCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);





        // show all events
        final ListView listView = findViewById(R.id.main_lv);
        EventRepository er = new EventRepository(getApplication());
        myCursor = er.getAllEventsCursor();
        final MyCursor adapter = new MyCursor(this, myCursor);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // if the event is completed, cannot modify it
                // if the event is incomplete, mark as complete or modify it
                Intent intent = new Intent(CursorActivity.this, EditorActivity.class);

                Cursor c = (Cursor)adapterView.getAdapter().getItem(position);
                int e_id = c.getInt(c.getColumnIndexOrThrow(EventAttrib.ID.toString()));
                intent.putExtra(EventAttrib.ID.toString(), e_id);

                startActivity(intent);
                finish();
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {

                new AlertDialog.Builder(CursorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Note")
                        .setTitle("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                EventRepository er = new EventRepository(getApplication());
                                Cursor c = (Cursor)adapterView.getAdapter().getItem(position);
                                Event selectedEvent = er.getEventById(c.getInt(c.getColumnIndexOrThrow(EventAttrib.ID.toString())));
                                er.deleteOneEvent(selectedEvent);


                                // refresh the activity without animation
                                recreate();

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_add) {
            startActivity(new Intent(CursorActivity.this, EditorActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
