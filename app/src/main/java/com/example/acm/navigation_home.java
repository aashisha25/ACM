package com.example.acm;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class navigation_home extends Fragment implements View.OnClickListener {

    RecyclerView recyclerViewEvents,recyclerViewAchieve;
    DatabaseReference myRef,myref1;
    Query query,query1;
    static ProgressBar bar_home;
    //CarouselView carouselView;
    TextView aboutAcmHeader, titleRecentAchieve, titleRecentEvents;
    NavController navController;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_home, container, false);
        myRef = FirebaseDatabase.getInstance().getReference().child("EVENTS");
        query = myRef.orderByChild("year").limitToLast(10);
        query.keepSynced(true);
        /*myref1 = FirebaseDatabase.getInstance().getReference().child("ACHIEVEMENTS");
        query1 = myref1.limitToLast(5);
        query1.keepSynced(true);*/
        bar_home = view.findViewById(R.id.progressHome);
        bar_home.setVisibility(View.VISIBLE);

        /*recyclerViewAchieve=(RecyclerView) view.findViewById(R.id.homeRecyclerAchieve);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        recyclerViewAchieve.setLayoutManager(layoutManager1);*/

        recyclerViewEvents =(RecyclerView) view.findViewById(R.id.homeRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewEvents.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        view.findViewById(R.id.fab_achievements).setOnClickListener(this);
        view.findViewById(R.id.fab_events).setOnClickListener(this);
        view.findViewById(R.id.fab_team).setOnClickListener(this);
        view.findViewById(R.id.homeSeeAll).setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Events, navigation_home.EventsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Events, navigation_home.EventsViewHolder>
                (Events.class,R.layout.home_event_model, navigation_home.EventsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(navigation_home.EventsViewHolder eventsViewHolder, final Events events, int i) {

                //eventsViewHolder.setTitle(events.getTitle());
                //eventsViewHolder.setDate(events.getDate());
                eventsViewHolder.setImage(events.getImageURL());
                eventsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(),DisplayEvents.class);
                        intent.putExtra("IMAGEURL",events.getImageURL());
                        intent.putExtra("TITLE",events.getTitle());
                        intent.putExtra("DESCRIPTION",events.getDescription());

                        startActivity(intent);
                    }
                });
            }
        };

        recyclerViewEvents.setAdapter(firebaseRecyclerAdapter);

        /*FirebaseRecyclerAdapter<Achievements, navigation_home.EventsViewHolderAchieve> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<Achievements, EventsViewHolderAchieve>
                (Achievements.class, R.layout.home_event_model, navigation_home.EventsViewHolderAchieve.class, query1) {
            @Override
            protected void populateViewHolder(navigation_home.EventsViewHolderAchieve eventsViewHolder, final Achievements achievements, int i) {

                //eventsViewHolder.setAchieveDes(achievements.getDescription());
                //eventsViewHolder.setAchieveTitle(achievements.getTitle());
                eventsViewHolder.setAchieveURL(achievements.getImageURL());
            }
        };
        recyclerViewAchieve.setAdapter(firebaseRecyclerAdapter1);*/
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public EventsViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title)
        {
            TextView textViewTitle = (TextView)mView.findViewById(R.id.recent_title);
            textViewTitle.setText(title);
        }
        public void setDate(String date)
        {
            TextView textViewDate = (TextView)mView.findViewById(R.id.recent_date);
            textViewDate.setText(date);
        }
        public void setImage(final String imageUrl)
        {
            bar_home.setVisibility(View.GONE);
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("IMAGES/").child(imageUrl);
            ImageView imageViewRecent = (ImageView)mView.findViewById(R.id.homeModelImage);
            GlideApp.with(mView.getContext())
                    .load(storageReference)
                    .into(imageViewRecent);
        }
    }

    /*public static class EventsViewHolderAchieve extends RecyclerView.ViewHolder {
        View mView;

        public EventsViewHolderAchieve(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setAchieveDes(String description) {
            TextView textViewDesc = (TextView) mView.findViewById(R.id.achieve_desc);
            textViewDesc.setText(description);
        }

        public void setAchieveTitle(String title) {
            TextView textViewTitle = (TextView) mView.findViewById(R.id.achieve_title);
            textViewTitle.setText(title);
        }

        public void setAchieveURL(final String imageUrl) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ACHIEVEMENTS/").child(imageUrl);
            ImageView imageViewAchieve = (ImageView)mView.findViewById(R.id.homeModelImage);
            GlideApp.with(mView.getContext())
                    .load(storageReference)
                    .into(imageViewAchieve);
        }
    }*/

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
            case R.id.homeSeeAll :
                navController.navigate(R.id.action_navigation_home_to_navigation_event);
                break;
        }
    }
}