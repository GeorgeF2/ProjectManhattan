package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityFindRideys extends AppCompatActivity {

    private Button cancel;
    private TextView rydersGotten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_rideys);

        cancel = findViewById(R.id.activity_find_ridey_cancel_button);
        rydersGotten = findViewById(R.id.activity_find_ridey_ryders_seats);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
