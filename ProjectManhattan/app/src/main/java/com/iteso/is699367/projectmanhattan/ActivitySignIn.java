package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Comparator;

public class ActivitySignIn extends AppCompatActivity {

    private Spinner collegeSpinner;
    private EditText email, name, password, confirmPass;
    private Button continueB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.activity_sign_in_email);
        name = findViewById(R.id.activity_sign_in_name);
        password = findViewById(R.id.activity_sign_in_password);
        confirmPass = findViewById(R.id.activity_sign_in_confirm_password);
        continueB = findViewById(R.id.activity_sign_in_continue);

        collegeSpinner = findViewById(R.id.activity_sign_in_spinner);

        ArrayAdapter<CharSequence> adapterCollege =  ArrayAdapter
                .createFromResource(this, R.array.college_array,
                        R.layout.support_simple_spinner_dropdown_item);

        adapterCollege.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(adapterCollege);

        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            email.setText(extras.getString("EMAIL"));
            password.setText(extras.getString("PASS"));
        }

        continueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, ActivityChoosing.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                                Intent.FLAG_ACTIVITY_NEW_TASK|
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
