package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3500);

    }
}
