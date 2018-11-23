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
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.is699367.projectmanhattan.Adapters.AdapterProfile;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.User;


public class FragmentProfile extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public TextView name, college, ratio, carModel;
    public ImageView star1, star2, star3, star4, star5, profilePicture;

    public FragmentProfile() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        User user = new User();
        user.setName("Jorge Pérez");
        user.setHasCar(true);
        user.setCollege("ITESO (Instituto Tecnológico de Estudios Superiores de Occidente)");
        user.setCarModel("Toyota Yaris 2015");

        name = view.findViewById(R.id.fragment_profile_name);
        college = view.findViewById(R.id.fragment_profile_college);
        ratio = view.findViewById(R.id.fragment_profile_drivey_ridey_ratio);
        carModel = view.findViewById(R.id.fragment_profile_car_model);
        star1 = view.findViewById(R.id.fragment_profile_star1);
        star2 = view.findViewById(R.id.fragment_profile_star2);
        star3 = view.findViewById(R.id.fragment_profile_star3);
        star4 = view.findViewById(R.id.fragment_profile_star4);
        star5 = view.findViewById(R.id.fragment_profile_star5);

        name.setText(user.getName());
        college.setText(user.getCollege());
        carModel.setText(user.getCarModel());

        star1.setImageResource(android.R.drawable.star_on);
        star2.setImageResource(android.R.drawable.star_on);
        star3.setImageResource(android.R.drawable.star_on);
        star4.setImageResource(android.R.drawable.star_on);
        star5.setImageResource(android.R.drawable.star_off);

        return view;
    }
}
