package com.anu.sample_dao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        // callback when item on BottomNavigationView is selected
        BottomNavigationView bnv = findViewById(R.id.bottom_nav_view);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                System.out.println("good");
                switch (menuItem.getItemId()) {
                    case R.id.bnv_item_1:
                        System.out.println("First item is selected");
                        break;
                    case R.id.bnv_item_2:
                        System.out.println("Second item is selected");
                        break;
                    case R.id.bnv_item_3:
                        System.out.println("Third item is selected");
                        break;
                }

                // update selecte state
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.bnv_item_1);


    }
}
