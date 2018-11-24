package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityChoosing extends AppCompatActivity {

    public static final String ROLE = "ROLE";
    private ImageView help;
    private TextView driveyHelp, rideyHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);


        driveyHelp = findViewById(R.id.activity_choosing_drivey_help);
        rideyHelp = findViewById(R.id.activity_choosing_ridey_help);

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


    public void appearHelp(View v) {


        if (driveyHelp.getVisibility() == View.GONE && rideyHelp.getVisibility() == View.GONE) {
            driveyHelp.setVisibility(View.VISIBLE);
            rideyHelp.setVisibility(View.VISIBLE);
        }
        else {
            driveyHelp.setVisibility(View.GONE);
            rideyHelp.setVisibility(View.GONE);
        }


    }


}
