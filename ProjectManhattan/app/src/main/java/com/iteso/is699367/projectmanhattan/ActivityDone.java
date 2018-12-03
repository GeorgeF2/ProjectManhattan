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
        cancel = findViewById(R.id.activity_done_cancel);

        FirebaseDatabase.getInstance().getReference().child("Rydes").child(FirebaseAuth.getInstance().getUid()).child("Ryders").addValueEventListener(new ValueEventListener() {
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

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
    private void cancel(){
        FirebaseDatabase.getInstance().getReference().child("Rydes").child(FirebaseAuth.getInstance().getUid()).setValue(null);
        Intent intent = new Intent(this, ActivityChoosing.class);
        startActivity(intent);
        finish();
    }
}
