package com.iteso.is699367.projectmanhattan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityWait extends AppCompatActivity {

    private TextView name, car;
    private Button cancel;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        String rydeId = new String();

        name = findViewById(R.id.activity_wait_name);
        car = findViewById(R.id.activity_wait_car);
        cancel = findViewById(R.id.activity_wait_cancel);


        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            rydeId = extras.getString("rydeid");
        }

        final String id = rydeId;
        sid = rydeId;

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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }
    private void cancel(){
        FirebaseDatabase.getInstance().getReference().child("Rydes").child(sid).child("Ryders").child(FirebaseAuth.getInstance().getUid()).setValue(null);
        finish();
    }
}
