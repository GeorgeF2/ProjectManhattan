package com.iteso.is699367.projectmanhattan.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.iteso.is699367.projectmanhattan.ActivityAddAddresses;
import com.iteso.is699367.projectmanhattan.ActivityLogin;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterAddresses;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class FragmentSettings extends Fragment {


    public FragmentSettings() {}

    Button so;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        so = view.findViewById(R.id.fragment_settings_sign_out);
        mAuth = FirebaseAuth.getInstance();

        so.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(getContext(), ActivityLogin.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void signOut() {
        mAuth.signOut();
    }
}
