package com.example.acm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class navigation_home extends Fragment implements View.OnClickListener {

    NavController navController;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController= Navigation.findNavController(view);
        view.findViewById(R.id.fab_achievements).setOnClickListener(this);
        view.findViewById(R.id.fab_events).setOnClickListener(this);
        view.findViewById(R.id.fab_team).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_achievements :
                navController.navigate(R.id.action_navigation_home_to_navigation_achievements);
                break;
            case R.id.fab_events :
                navController.navigate(R.id.action_navigation_home_to_navigation_event);
                break;
            case R.id.fab_team :
                navController.navigate(R.id.action_navigation_home_to_navigation_team);
                break;
        }

    }
}