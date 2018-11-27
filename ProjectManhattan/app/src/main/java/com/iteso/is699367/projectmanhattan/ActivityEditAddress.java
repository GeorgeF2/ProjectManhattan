package com.iteso.is699367.projectmanhattan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEditAddress extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databse;
    private EditText name, street, city, state;
    private Button done, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        mAuth = FirebaseAuth.getInstance();
        databse = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).child("Addresses");

        name = findViewById(R.id.activity_add_addresses_nickname);
        street = findViewById(R.id.activity_add_addresses_street);
        city = findViewById(R.id.activity_add_addresses_city);
        state = findViewById(R.id.activity_add_addresses_state);
        done = findViewById(R.id.activity_add_addresses_done);
        cancel = findViewById(R.id.activity_add_addresses_cancel);

        databse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("addressName").getValue().toString());
                street.setText(dataSnapshot.child("street").getValue().toString());
                city.setText(dataSnapshot.child("city").getValue().toString());
                state.setText(dataSnapshot.child("state").getValue().toString());
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
                saveAddress();
            }
        });
    }

    private void saveAddress(){
        databse.child("addressName").setValue(name.getText().toString());
        databse.child("street").setValue(street.getText().toString());
        databse.child("city").setValue(city.getText().toString());
        databse.child("state").setValue(state.getText().toString());
        finish();
    }
}
