package com.anu.sample_dao.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.anu.sample_dao.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamePlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);



        BottomNavigationView bnv = findViewById(R.id.play_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                System.out.println("good");
                switch (menuItem.getItemId()) {
                    case R.id.play_item_1: // go back
                        finish(); // go back to home
                        break;

                    case R.id.play_item_2:
                        System.out.println("Second item is selected");
                        break;

                    case R.id.play_item_3:
                        System.out.println("Third item is selected");
                        break;
                }

                // update selecte state
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.play_item_2);
    }
}
