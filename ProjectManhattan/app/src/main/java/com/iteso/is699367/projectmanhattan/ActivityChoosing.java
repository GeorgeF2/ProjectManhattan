package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityChoosing extends AppCompatActivity {

    public static final String ROLE = "ROLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
    }

    public void onRoleChosen (View v) {
        String role = "";
        switch(v.getId()) {
            case R.id.activity_choose_drivey:
                role = "Drivey";
                break;
            case R.id.activity_choose_ridey:
                role = "Ridey";
                break;
        }

        Intent intent = new Intent(ActivityChoosing.this, ActivityMain.class);
        intent.putExtra(ROLE, role);
        startActivity(intent);
    }
}
