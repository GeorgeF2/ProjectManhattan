package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
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
import com.iteso.is699367.projectmanhattan.Fragments.FragmentAddresses;

public class ActivityEditAddress extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databse;
    private EditText name, street, city, state;
    private Button done, cancel;
    private String addrId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        name = findViewById(R.id.activity_edit_address_nickname);
        street = findViewById(R.id.activity_edit_address_street);
        city = findViewById(R.id.activity_edit_address_city);
        state = findViewById(R.id.activity_edit_address_state);
        done = findViewById(R.id.activity_edit_address_done);
        cancel = findViewById(R.id.activity_edit_address_cancel);

        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            addrId = extras.getString("ADDRID");
        }

        mAuth = FirebaseAuth.getInstance();
        databse = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).child("Addresses").child(addrId);

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
                Intent intent = new Intent(ActivityEditAddress.this, ActivityMain.class);
                startActivity(intent);
            }
        });
    }

    private void saveAddress(){
        databse.child("addressName").setValue(name.getText().toString());
        databse.child("street").setValue(street.getText().toString());
        databse.child("city").setValue(city.getText().toString());
        databse.child("state").setValue(state.getText().toString());
    }
}
