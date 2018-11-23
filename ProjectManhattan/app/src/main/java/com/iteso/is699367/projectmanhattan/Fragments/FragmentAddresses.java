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

import com.iteso.is699367.projectmanhattan.ActivityAddAddresses;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterAddresses;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class FragmentAddresses extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentAddresses() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_addresses, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_addresses_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Addresses> newAddresses = new ArrayList<>();

        Addresses address = new Addresses();
        address.setStreet("Jesús García #3062");
        address.setCity("Guadalajara");
        address.setState("Jalisco");
        address.setAddressName("Home");

        newAddresses.add(address);

        Addresses address1 = new Addresses();
        address1.setAddress("Anillo Perif. Sur Manuel Gómez Morín 8585", "Tlaquepaque", "Jalisco");

        newAddresses.add(address);
        newAddresses.add(address1);

        adapter = new AdapterAddresses(getActivity(), newAddresses);
        recyclerView.setAdapter(adapter);

        FloatingActionButton addAddress = view.findViewById(R.id.addresses_tab_fab);

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityAddAddresses.class);
                startActivity(intent);
            }
        });

        return view;
    }




}
