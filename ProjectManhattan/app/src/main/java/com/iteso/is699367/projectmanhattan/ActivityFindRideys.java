package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityFindRideys extends AppCompatActivity {

    private Button cancel, sd;
    private TextView rydersGotten;
    private int totalSeats, remSeats;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_rideys);

        database = FirebaseDatabase.getInstance().getReference().child("Rydes");

        cancel = findViewById(R.id.activity_find_ridey_cancel_button);
        sd = findViewById(R.id.activity_find_done);
        rydersGotten = findViewById(R.id.activity_find_ridey_ryders_seats);
        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            totalSeats = Integer.valueOf(extras.getString("where"));
        }

        FirebaseDatabase.getInstance().getReference().child("Rydes").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("totalSeats").getValue() != null)
                    totalSeats = Integer.valueOf(dataSnapshot.child("totalSeats").getValue().toString());
                if (dataSnapshot.hasChild("Ryders")){
                    remSeats = totalSeats - (int) dataSnapshot.child("Ryders").getChildrenCount();
                }
                else{
                    remSeats = totalSeats;
                }
                rydersGotten.setText(remSeats + " seats remaining");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Rydes").child(FirebaseAuth.getInstance().getUid()).setValue(null);
                Intent intent = new Intent(ActivityFindRideys.this, ActivityChoosing.class);
                startActivity(intent);
                finish();
            }
        });

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFindRideys.this, ActivityDone.class);
                startActivity(intent);
            }
        });

        rydersGotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFindRideys.this, ActivityRydersGotten.class);
                startActivity(intent);
            }
        });


    }
}
