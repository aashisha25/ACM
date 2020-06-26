package com.example.acm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DisplayPastEvents extends AppCompatActivity {
    TextView title,description;
    ImageView image;
    String Title , Parent, Year, Description, ImageURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);
        Intent intent = getIntent();
        Parent = intent.getStringExtra("PARENT");
        Title = intent.getStringExtra("TITLE");
        Year=Parent.substring(0,4);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("EVENTS");
        final Events event = new Events();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    event.setTitle(ds.getValue(Events.class).getTitle());
                    event.setDate(ds.getValue(Events.class).getDate());
                    event.setDescription(ds.getValue(Events.class).getDescription());
                    event.setImageURL(ds.getValue(Events.class).getImageURL());

                    if(event.getTitle().equals(Title)&&event.getDate().trim().equals(Year))
                    {
                        Description = event.getDescription();
                        ImageURL = event.getImageURL();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        title=findViewById(R.id.textViewTitle);
        description=findViewById(R.id.textViewDescription);
        image=findViewById(R.id.imageViewDisplay);



        title.setText(Title);
        description.setText(Description);
        //StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("IMAGES/").child(ImageURL);
        //GlideApp.with(this)
                //.load(storageReference)
                //.into(image);
    }
}
