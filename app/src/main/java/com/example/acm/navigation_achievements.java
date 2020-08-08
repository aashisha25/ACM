package com.example.acm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class navigation_achievements extends Fragment {

    DatabaseReference myref;
    RecyclerView recyclerView;
    static ProgressBar pbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myref = FirebaseDatabase.getInstance().getReference().child("ACHIEVEMENTS");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pbar = view.findViewById(R.id.content_bar);
        pbar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Achievements, navigation_achievements.EventsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Achievements, navigation_achievements.EventsViewHolder>
                (Achievements.class, R.layout.achievement_model, navigation_achievements.EventsViewHolder.class, myref) {
            @Override
            protected void populateViewHolder(navigation_achievements.EventsViewHolder eventsViewHolder, final Achievements achievements, int i) {

                eventsViewHolder.setAchieveDes(achievements.getAchieveDes());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public EventsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setAchieveDes(String title) {
            TextView textViewTitle = (TextView) mView.findViewById(R.id.achieve_msg);
            textViewTitle.setText(title);
            pbar.setVisibility(View.GONE);
        }
    }
}