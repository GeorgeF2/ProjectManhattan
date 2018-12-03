package com.iteso.is699367.projectmanhattan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityDone extends AppCompatActivity {
    private TextView done;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        done = findViewById(R.id.activity_done_car);

        FirebaseDatabase.getInstance().getReference().child("Rydes").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            String names = "";
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    names += snapshot.child("name").getValue().toString() + ", ";
                }
                done.setText(names);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
