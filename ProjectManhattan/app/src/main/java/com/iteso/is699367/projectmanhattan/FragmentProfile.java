package com.iteso.is699367.projectmanhattan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.is699367.projectmanhattan.beans.User;


public class FragmentProfile extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentProfile() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_profile_recycler_view);

        recyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        User user = new User();
        user.setName("Jorge Pérez");
        user.setHasCar(true);
        user.setCollege("ITESO (Instituto Tecnológico de Estudios Superiores de Occidente)");
        user.setCarModel("Toyota Yaris 2014");

        mAdapter = new AdapterProfile(getActivity(), user);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
