package com.iteso.is699367.projectmanhattan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class FragmentDriveyHome extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public FragmentDriveyHome() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drivey_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_drivey_home_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Addresses> newAddresses = new ArrayList<>();

        Addresses address = new Addresses();
        address.setAddress("Jesús García #3062, Lomas de Providencia, Guadalajara, Jalisco");
        address.setAddressName("Home");

        newAddresses.add(address);

        adapter = new AdapterDriveyHome(getActivity(), newAddresses);
        recyclerView.setAdapter(adapter);
        return view;
    }
}