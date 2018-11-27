package com.iteso.is699367.projectmanhattan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEditProfile extends AppCompatActivity {

    private Spinner collegeSpinner;
    private EditText name, carModel;
    private Button done;
    private Switch car;
    private FirebaseAuth mAuth;
    private DatabaseReference databse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.activity_edit_profile_name);
        carModel = findViewById(R.id.activity_edit_profile_car_model);
        car = findViewById(R.id.activity_edit_profile_car_switch);
        done = findViewById(R.id.activity_edit_profile_done);
        mAuth = FirebaseAuth.getInstance();
        databse = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid());

        collegeSpinner = findViewById(R.id.activity_edit_profile_spinner);

        ArrayAdapter<CharSequence> adapterCollege =  ArrayAdapter
                .createFromResource(this, R.array.college_array,
                        R.layout.support_simple_spinner_dropdown_item);

        adapterCollege.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        collegeSpinner.setAdapter(adapterCollege);

        // Read from the database
        databse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               name.setText(dataSnapshot.child("name").getValue().toString());
               carModel.setText(dataSnapshot.child("carModel").getValue().toString());
               car.setChecked(dataSnapshot.child("hasCar").getValue().toString().contains("true"));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(null, "Failed to read value.", error.toException());
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });


    }

    private void saveProfile(){
        databse.child("name").setValue(name.getText().toString());
        databse.child("hasCar").setValue(car.isChecked());
        databse.child("college").setValue(collegeSpinner.getSelectedItem().toString());
        databse.child("carModel").setValue(carModel.getText().toString());
        finish();
    }
}
