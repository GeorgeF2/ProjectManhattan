package com.iteso.is699367.projectmanhattan.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.User;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.ViewHolder> {

    private User user;
    private Context context;

    public AdapterProfile(Context context, User user) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.profile, viewGroup, false);
        AdapterProfile.ViewHolder vh = new AdapterProfile.ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, college, ratio, carModel;
        public ImageView star1, star2, star3, star4, star5, profilePicture;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.fragment_profile_name);
            college = v.findViewById(R.id.fragment_profile_college);
            ratio = v.findViewById(R.id.fragment_profile_drivey_ridey_ratio);
            carModel = v.findViewById(R.id.fragment_profile_car_model);
            star1 = v.findViewById(R.id.fragment_profile_star1);
            star2 = v.findViewById(R.id.fragment_profile_star2);
            star3 = v.findViewById(R.id.fragment_profile_star3);
            star4 = v.findViewById(R.id.fragment_profile_star4);
            star5 = v.findViewById(R.id.fragment_profile_star5);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(user.getName());
        viewHolder.college.setText(user.getCollege());
        //viewHolder.ratio.setText(user.ge0);
        viewHolder.carModel.setText(user.getCarModel());

        viewHolder.star1.setImageResource(android.R.drawable.star_on);
        viewHolder.star2.setImageResource(android.R.drawable.star_on);
        viewHolder.star3.setImageResource(android.R.drawable.star_on);
        viewHolder.star4.setImageResource(android.R.drawable.star_big_on);
        viewHolder.star5.setImageResource(android.R.drawable.star_off);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public User getUserFireBase() {
        /*IMPLEMENTATION FOR FIREBASE*/
        return null;
    }
}
