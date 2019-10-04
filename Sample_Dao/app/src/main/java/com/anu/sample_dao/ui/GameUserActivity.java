package com.anu.sample_dao.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anu.sample_dao.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GameUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);



        BottomNavigationView bnv = findViewById(R.id.user_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                System.out.println("good");
                switch (menuItem.getItemId()) {
                    case R.id.user_item_1: // go back
                        finish();
                        break;
                    case R.id.user_item_2:
                        System.out.println("Second item is selected");
                        break;
                    case R.id.user_item_3:
                        System.out.println("Third item is selected");
                        break;
                }

                // update selecte state
                return true;
            }
        });
        bnv.setSelectedItemId(R.id.user_item_2);
    }
}
