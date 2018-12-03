package com.iteso.is699367.projectmanhattan.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterDriveyHome;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterRideyHome;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FragmentRideyHome extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentRideyHome() {}

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_ridey_home, container, false);

            final RecyclerView recyclerView = view.findViewById(R.id.fragment_ridey_home_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Addresses> myAddresses = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.child("Rydes").getChildren()){
                        String name, street = new String(), city = new String(), state = new String();
                        name = dataSnapshot.child("Users").child(snapshot.getKey()).child("name").getValue().toString();
                        for (DataSnapshot snap : dataSnapshot.child("Users").child(snapshot.getKey()).child("Addresses").getChildren()){
                            if (snap.child("addressName").getValue().toString().contains(snapshot.child("addr").getValue().toString())){
                                street = snap.child("street").getValue().toString();
                                city = snap.child("city").getValue().toString();
                                state = snap.child("state").getValue().toString();
                            }
                        }
                        Addresses address = new Addresses(name, street, city, state);
                        myAddresses.add(address);
                        Log.d(TAG, "Value is: " + address);
                    }
                    adapter = new AdapterRideyHome(getActivity(), myAddresses);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return view;
        }
}
