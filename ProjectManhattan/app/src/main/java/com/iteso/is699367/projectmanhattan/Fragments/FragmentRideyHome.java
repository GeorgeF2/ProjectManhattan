package com.iteso.is699367.projectmanhattan.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.is699367.projectmanhattan.Adapters.AdapterDriveyHome;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterRideyHome;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class FragmentRideyHome extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentRideyHome() {}

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_ridey_home, container, false);

            RecyclerView recyclerView = view.findViewById(R.id.fragment_ridey_home_recycler_view);
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

            adapter = new AdapterRideyHome(getActivity(), newAddresses);
            recyclerView.setAdapter(adapter);
            return view;
        }
}
