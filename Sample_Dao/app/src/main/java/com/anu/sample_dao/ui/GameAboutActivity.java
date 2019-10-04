package com.anu.sample_dao.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.anu.sample_dao.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GameAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        // 2 - bg be transparent
        // srcCompat has wrong format
        // use background instead
//        ImageView bg = findViewById(R.id.about_bg);
//        bg.setBackground(null);




        // 3 - callback when item on BottomNavigationView is selected
        // FIXME: but it cause the scene transition failed if it is in onCreate()
        // Solved: becaue first item is default selected and has finished().
        // Thus when transit to About, it will go back immediately
        // Solution: choose another selected item
        BottomNavigationView bnv = findViewById(R.id.about_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                System.out.println("good");
                switch (menuItem.getItemId()) {
                    case R.id.about_item_1: // go back
                        finish(); // go back to home
                        break;

                    case R.id.about_item_2:
                        System.out.println("Second item is selected");
                        break;

                    case R.id.about_item_3:
                        System.out.println("Third item is selected");
                        break;

                    case R.id.about_item_4:
                        System.out.println("Third item is selected");
                        break;
                }

                // update selecte state
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.about_item_2);

    }




}
