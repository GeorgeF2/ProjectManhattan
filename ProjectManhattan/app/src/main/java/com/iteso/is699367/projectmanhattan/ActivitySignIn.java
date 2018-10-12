package com.iteso.is699367.projectmanhattan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Comparator;

public class ActivitySignIn extends AppCompatActivity {

    private Spinner collegeSpinner;
    private EditText email, name, password, confirmPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.activity_sign_in_email);
        name = findViewById(R.id.activity_sign_in_name);
        password = findViewById(R.id.activity_sign_in_password);
        confirmPass = findViewById(R.id.activity_sign_in_confirm_password);

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

    }
}
