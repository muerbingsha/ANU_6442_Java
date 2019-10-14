//package com.anu.sampledao;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import com.anu.sampledao.db.Event;
//import com.anu.sampledao.db.EventRepository;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CursorActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Events");
//
//
//        // show all events
//        ListView listView = findViewById(R.id.main_lv);
//        EventRepository er = new EventRepository(getApplication());
//        List<Event> events = er.getAllEvents();
//        Cursor cursor = null;
//        MyCursor adapter = new MyCursor(this, cursor);
//        listView.setAdapter(adapter);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // if the event is completed, cannot modify it
//                // if the event is incomplete, mark as complete or modify it
//                Intent intent = new Intent(CursorActivity.this, EditorActivity.class);
//
//                EventRepository er = new EventRepository(getApplication());
//                String title = ((TextView) view).getText().toString();
//                Event e = er.getEventByTitle(title);
//                intent.putExtra("title", e.title);
//                intent.putExtra("location", e.location);
//                intent.putExtra("start", e.starts);
//                intent.putExtra("end", e.ends);
//                intent.putExtra("alert", e.alert);
//                intent.putExtra("url", e.url);
//                intent.putExtra("notes", e.notes);
//
//
//                startActivity(intent);
//            }
//        });
//
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
//                // make it final for later access
//                final int itemDelete = position;
//
//
//                new AlertDialog.Builder(CursorActivity.this)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Delete Note")
//                        .setTitle("Do you want to delete this note?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                titles.remove(itemDelete);
//                                adapter.notifyDataSetChanged();
//
//                                // delete by using Dao
//                                TextView child = (TextView) adapterView.getChildAt(itemDelete);
//                                EventRepository er = new EventRepository(getApplication());
//                                Event targetEvent = er.getEventByTitle(child.getText().toString());
//                                er.deleteOneEvent(targetEvent);
//
//                            }
//                        })
//                        .setNegativeButton("No",null)
//                        .show();
//                return true;
//            }
//        });
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.main_add) {
//            startActivity(new Intent(CursorActivity.this, EditorActivity.class));
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
