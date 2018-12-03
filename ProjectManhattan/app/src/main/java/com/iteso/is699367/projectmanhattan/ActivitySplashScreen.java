package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {


    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if(firebaseUser == null) {
                    Intent intent = new  Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    FirebaseDatabase.getInstance().getReference().child("Rydes").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(firebaseUser.getUid())){
                                Intent intent = new Intent(ActivitySplashScreen.this, ActivityFindRideys.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(ActivitySplashScreen.this, ActivityChoosing.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1500);

    }
}
