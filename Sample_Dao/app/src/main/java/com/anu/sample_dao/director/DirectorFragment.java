/**
 * Reference: https://github.com/lomza/movies-room
 */



package com.anu.sample_dao.director;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;



public class DirectorFragment extends Fragment {

    private DirectorViewModel dvm;
    private DirectorAdapter da;
    private Context context;


    /**
     * Singleton
     */
    public static DirectorFragment newInstance(){ return new DirectorFragment(); }


    /**
     * lift cycles
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void initData() {
        dvm = ViewModelProviders.of(this).get(DirectorViewModel.class);


    }


}
