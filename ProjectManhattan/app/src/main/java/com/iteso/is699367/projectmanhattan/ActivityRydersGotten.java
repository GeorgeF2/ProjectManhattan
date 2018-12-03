package com.iteso.is699367.projectmanhattan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterDriveyHome;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterRydersGotten;
import com.iteso.is699367.projectmanhattan.beans.Addresses;
import com.iteso.is699367.projectmanhattan.beans.User;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class ActivityRydersGotten extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference myRef;
    FirebaseUser user;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ryders_gotten);

        recyclerView = findViewById(R.id.activity_ryders_recycler_view);

        recyclerView.setHasFixedSize(true);

        users = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        User user = new User("Santiago", "ITESO", "None", false);

        users.add(user);

        user.setName("Jorge");
        user.setCollege("ITESO");
        user.setCarModel("None");
        user.setHasCar(false);

        users.add(user);


        adapter = new AdapterRydersGotten(this, users);

        recyclerView.setAdapter(adapter);


    }

}
