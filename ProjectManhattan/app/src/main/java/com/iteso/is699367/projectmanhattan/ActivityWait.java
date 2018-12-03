package com.iteso.is699367.projectmanhattan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityWait extends AppCompatActivity {

    private TextView name, car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        String rydeId = new String();

        name = findViewById(R.id.activity_wait_name);
        car = findViewById(R.id.activity_wait_car);


        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            rydeId = extras.getString("rydeid");
        }

        final String id = rydeId;

        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dryName = dataSnapshot.child("Users").child(id).child("name").getValue().toString();
                String dryCar = dataSnapshot.child("Users").child(id).child("carModel").getValue().toString();
                String dryTime = dataSnapshot.child("Rydes").child(id).child("time").getValue().toString();

                name.setText("Please meet " + dryName + " at the rides location at " + dryTime);
                car.setText("Drivey has this car model: " + dryCar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
