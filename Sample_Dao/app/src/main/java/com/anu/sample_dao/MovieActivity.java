package com.anu.sample_dao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anu.sample_dao.db.MoviesDatabase;
import com.anu.sample_dao.director.DirectorFragment;
import com.anu.sample_dao.movie.MovieFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MovieActivity extends AppCompatActivity {

    private boolean MOVIES_SHOWN = true;
    private Fragment shownFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        setToolbar(getString(R.string.app_name));

        initView();

    }

    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.movie_toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }




    private void initView() {
        BottomNavigationView navigation = findViewById(R.id.movie_nav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_movies:
                        MOVIES_SHOWN = true;
                        showFragment(MovieFragment.newInstance());
                        return true;
                    case R.id.nav_directors:
                        MOVIES_SHOWN = false;
                        showFragment(DirectorFragment.newInstance());
                        return true;
                }
                return false;
            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showSaveDialog();
//            }
//        });
    }


    private void showFragment(final Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
//        fragmentTransaction.commitNow();
//        shownFragment = fragment;
    }

    private void showSaveDialog() {
//        DialogFragment dialogFragment;
//        String tag;
//        if (MOVIES_SHOWN) {
//            dialogFragment = MovieDialogFragment.newInstance(null, null);
//            tag = TAG_DIALOG_MOVIE_SAVE;
//        } else {
//            dialogFragment = DirectorDialogFragment.newInstance(null);
//            tag = TAG_DIALOG_DIRECTOR_SAVE;
//        }
//
//        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_overflow, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void deleteCurrentListData() {
//        if (MOVIES_SHOWN) {
//            ((MovieFragment) shownFragment).removeData();
//        } else {
//            ((DirectorFragment) shownFragment).removeData();
//        }
    }

    private void reCreateDatabase() {
        MoviesDatabase.getDatabase(this).clearDb();
    }

}
