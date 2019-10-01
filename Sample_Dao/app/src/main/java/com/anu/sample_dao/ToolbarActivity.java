package com.anu.sample_dao;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // to compat lower version



public class ToolbarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        // 1 - show toolbar
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        // it cause program crash
        // check logcat
        // add this line in Manifest.xml android:theme="@style/Theme.Design.Light.NoActionBar"
        // to get rid of original toobar
        setSupportActionBar(toolbar);


        // 2 - modify defautl title and subtitle
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Page");
        }
        toolbar.setSubtitle("Test Subtitle");
        toolbar.inflateMenu(R.menu.tool_main);


        // 3 - Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        // 4 - Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    }


    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool_main, menu);
        return true;
    }
}


