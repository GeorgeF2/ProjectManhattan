package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.utils.TimePickerFragment;

public class ActivityStart extends AppCompatActivity {

    private TextView where_to;
    static private EditText seats, time;
    private Button set_time, continueSeats, cancelSeats;
    String addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        where_to = findViewById(R.id.activity_start_where_to);
        seats = findViewById(R.id.activity_start_seats);
        time = findViewById(R.id.activity_start_time);
        set_time = findViewById(R.id.activity_start_set);
        continueSeats = findViewById(R.id.activity_start_continue);
        cancelSeats = findViewById(R.id.activity_start_cancel);

        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            addr = extras.getString("where");
        }

        where_to.setText("Driving to: " + addr);



        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtimePicker();
            }
        });

        continueSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conitnue();
            }
        });

        cancelSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void showtimePicker(){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    private void conitnue(){

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Rydes");
        database.child(FirebaseAuth.getInstance().getUid()).child("time").setValue(time.getText().toString());
        database.child(FirebaseAuth.getInstance().getUid()).child("totalSeats").setValue(seats.getText().toString());
        database.child(FirebaseAuth.getInstance().getUid()).child("addr").setValue(addr);

        Intent intent = new Intent(this, ActivityFindRideys.class);
        startActivity(intent);
        finish();
    }

    private void cancel(){
        finish();
    }
    public static void setTheTime(int h, int m){
        time.setText(String.valueOf(h) + ":" + String.valueOf(m));
    }
}
