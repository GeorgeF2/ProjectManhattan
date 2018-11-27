package com.iteso.is699367.projectmanhattan.Fragments;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.ActivityAddAddresses;
import com.iteso.is699367.projectmanhattan.ActivityEditProfile;
import com.iteso.is699367.projectmanhattan.ActivityLogin;
import com.iteso.is699367.projectmanhattan.Adapters.AdapterProfile;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.User;
import com.squareup.picasso.Picasso;


public class FragmentProfile extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public TextView name, college, ratio, carModel;
    public ImageView star1, star2, star3, star4, star5, profilePicture;
    public Button edit, sign_out;
    public FirebaseAuth mAuth;

    public FragmentProfile() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final FirebaseUser authUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = authUser.getUid();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference fbUser = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        final User user = new User();

        fbUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                user.setName(dataSnapshot.child("name").getValue().toString());
                user.setHasCar(true);
                user.setCollege(dataSnapshot.child("college").getValue().toString());
                user.setCarModel(dataSnapshot.child("carModel").getValue().toString());
                if (dataSnapshot.hasChild("rating"))
                    setRating(Integer.valueOf(dataSnapshot.child("rating").getValue().toString()));
                else
                    FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("rating").setValue("0");

                name.setText(user.getName());
                college.setText(user.getCollege());
                carModel.setText(user.getCarModel());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(null, "Failed to read value.", error.toException());
            }
        });


        name = view.findViewById(R.id.fragment_profile_name);
        college = view.findViewById(R.id.fragment_profile_college);
        ratio = view.findViewById(R.id.fragment_profile_drivey_ridey_ratio);
        carModel = view.findViewById(R.id.fragment_profile_car_model);
        profilePicture = view.findViewById(R.id.fragment_profile_picture);
        Picasso.with(this.getContext()).load(authUser.getPhotoUrl()).into(profilePicture);
        edit = view.findViewById(R.id.fragment_profile_edit);
        sign_out = view.findViewById(R.id.fragment_profile_sign_out);
        star1 = view.findViewById(R.id.fragment_profile_star1);
        star2 = view.findViewById(R.id.fragment_profile_star2);
        star3 = view.findViewById(R.id.fragment_profile_star3);
        star4 = view.findViewById(R.id.fragment_profile_star4);
        star5 = view.findViewById(R.id.fragment_profile_star5);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), ActivityLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        return view;
    }

    private void editProfile(){
        Intent intent = new Intent(getContext(), ActivityEditProfile.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void setRating(int n){
        switch (n){
            case 1:
                star1.setImageResource(android.R.drawable.star_on);
                star2.setImageResource(android.R.drawable.star_off);
                star3.setImageResource(android.R.drawable.star_off);
                star4.setImageResource(android.R.drawable.star_off);
                star5.setImageResource(android.R.drawable.star_off);
                break;
            case 2:
                star1.setImageResource(android.R.drawable.star_on);
                star2.setImageResource(android.R.drawable.star_on);
                star3.setImageResource(android.R.drawable.star_off);
                star4.setImageResource(android.R.drawable.star_off);
                star5.setImageResource(android.R.drawable.star_off);
                break;
            case 3:
                star1.setImageResource(android.R.drawable.star_on);
                star2.setImageResource(android.R.drawable.star_on);
                star3.setImageResource(android.R.drawable.star_on);
                star4.setImageResource(android.R.drawable.star_off);
                star5.setImageResource(android.R.drawable.star_off);
                break;
            case 4:
                star1.setImageResource(android.R.drawable.star_on);
                star2.setImageResource(android.R.drawable.star_on);
                star3.setImageResource(android.R.drawable.star_on);
                star4.setImageResource(android.R.drawable.star_on);
                star5.setImageResource(android.R.drawable.star_off);
                break;
            case 5:
                star1.setImageResource(android.R.drawable.star_on);
                star2.setImageResource(android.R.drawable.star_on);
                star3.setImageResource(android.R.drawable.star_on);
                star4.setImageResource(android.R.drawable.star_on);
                star5.setImageResource(android.R.drawable.star_on);
                break;
        }

    }
}
