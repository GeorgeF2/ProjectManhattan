package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Comparator;

public class ActivitySignIn extends AppCompatActivity {

    private Spinner collegeSpinner;
    private EditText email, name, password, confirmPass;
    private Button continueB;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.activity_sign_in_email);
        name = findViewById(R.id.activity_sign_in_name);
        password = findViewById(R.id.activity_sign_in_password);
        confirmPass = findViewById(R.id.activity_sign_in_confirm_password);
        continueB = findViewById(R.id.activity_sign_in_continue);
        mAuth = FirebaseAuth.getInstance();

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
                createAccount(email.getText().toString(), password.getText().toString());
            }
        });
    }



    public void createAccount(String email, String password){
        if (!validateForm()) {
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(null, "createUserWithEmail:success");
                            Intent intent = new Intent(ActivitySignIn.this, ActivityChoosing.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(null, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ActivitySignIn.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String emails = email.getText().toString();
        if (TextUtils.isEmpty(emails)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String passwords = password.getText().toString();
        if (TextUtils.isEmpty(passwords)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
