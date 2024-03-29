package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iteso.is699367.projectmanhattan.Fragments.FragmentAddresses;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

public class ActivityAddAddresses extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseUser user;
    EditText nickname, street, city, state;
    Button done, cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addresses);

        nickname = findViewById(R.id.activity_add_addresses_nickname);
        street = findViewById(R.id.activity_add_addresses_street);
        city = findViewById(R.id.activity_add_addresses_city);
        state = findViewById(R.id.activity_add_addresses_state);
        done = findViewById(R.id.activity_add_addresses_done);
        cancel = findViewById(R.id.activity_add_addresses_cancel);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnTo();
            }
        });

        myRef = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();


    }
    private void returnTo() {
        finish();
    }

    private void addAddress(){
        if (!validateForm())
            return;

        String userId = user.getUid();
        String AddId = myRef.child("Users").child(userId).child("Addresses").push().getKey();

        Addresses address = new Addresses(nickname.getText().toString(), street.getText().toString(), city.getText().toString(), state.getText().toString());

        myRef.child("Users").child(userId).child("Addresses").child(AddId).setValue(address);

       returnTo();
    }

    private boolean validateForm() {
        boolean valid = true;

        String nicknames = nickname.getText().toString();
        if (TextUtils.isEmpty(nicknames)) {
            nickname.setError("Required.");
            valid = false;
        } else {
            nickname.setError(null);
        }

        String streets = street.getText().toString();
        if (TextUtils.isEmpty(streets)) {
            street.setError("Required.");
            valid = false;
        } else {
            street.setError(null);
        }
        String citys = city.getText().toString();
        if (TextUtils.isEmpty(citys)) {
            city.setError("Required.");
            valid = false;
        } else {
            city.setError(null);
        }
        String states = state.getText().toString();
        if (TextUtils.isEmpty(states)) {
            state.setError("Required.");
            valid = false;
        } else {
            state.setError(null);
        }

        return valid;
    }
}
