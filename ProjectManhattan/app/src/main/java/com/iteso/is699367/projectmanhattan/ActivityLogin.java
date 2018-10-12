package com.iteso.is699367.projectmanhattan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    private Button bLogin, signIn;
    private EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = findViewById(R.id.activity_login_button);
        signIn = findViewById(R.id.activity_login_sign_in_button);
        email = findViewById(R.id.activity_login_email);
        password = findViewById(R.id.activity_login_password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignIn.class);
                intent.putExtra("EMAIL", email.getText().toString());
                intent.putExtra("PASS", password.getText().toString());
                startActivity(intent);
            }
        });

    }
}
