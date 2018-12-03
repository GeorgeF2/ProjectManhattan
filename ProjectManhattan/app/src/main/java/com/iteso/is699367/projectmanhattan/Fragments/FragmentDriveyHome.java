package com.iteso.is699367.projectmanhattan.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterDriveyHome;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FragmentDriveyHome extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseReference myRef;
    FirebaseUser user;
    public FragmentManager tfm;

    public FragmentDriveyHome() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drivey_home, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.fragment_drivey_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        tfm = this.getFragmentManager();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Addresses");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Addresses> myAddresses = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Addresses address = new Addresses(snapshot.child("addressName").getValue().toString(), snapshot.child("street").getValue().toString(), snapshot.child("city").getValue().toString(), snapshot.child("state").getValue().toString());
                    myAddresses.add(address);
                    Log.d(TAG, "Value is: " + address);
                }
                adapter = new AdapterDriveyHome(getActivity(), myAddresses);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return view;
    }


}