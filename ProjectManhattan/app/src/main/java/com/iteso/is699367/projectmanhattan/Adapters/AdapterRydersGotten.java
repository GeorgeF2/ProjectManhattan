package com.iteso.is699367.projectmanhattan.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.ActivityEditAddress;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;
import com.iteso.is699367.projectmanhattan.beans.User;

import java.util.ArrayList;

public class AdapterRydersGotten extends RecyclerView.Adapter<AdapterRydersGotten.ViewHolder> {

    private ArrayList<User> ryders;
    private Context context;
    DatabaseReference myRef;
    FirebaseUser user;
    String delId = "placeholder";

    public AdapterRydersGotten(Context context, ArrayList<User> myDataSet) {
        ryders = myDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRydersGotten.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.ryders_in, viewGroup, false);
        AdapterRydersGotten.ViewHolder vh = new AdapterRydersGotten.ViewHolder(v);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Addresses");


        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rydersLayout;
        private Button removeRyder;
        private TextView rydersName;
        private ImageView ryderStar1, ryderStar2, ryderStar3, ryderStar4, ryderStar5, profilePic;

        public ViewHolder(View v) {
            super(v);
            rydersLayout = itemView.findViewById(R.id.ryders_linear_layout);
            removeRyder = itemView.findViewById(R.id.ryders_in_remove);
            rydersName = itemView.findViewById(R.id.ryders_in_ryder_name);
            ryderStar1 = itemView.findViewById(R.id.ryders_in_star1);
            ryderStar2 = itemView.findViewById(R.id.ryders_in_star2);
            ryderStar3 = itemView.findViewById(R.id.ryders_in_star3);
            ryderStar4 = itemView.findViewById(R.id.ryders_in_star4);
            ryderStar5 = itemView.findViewById(R.id.ryders_in_star5);
            profilePic = itemView.findViewById(R.id.ryders_profile_pic);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRydersGotten.ViewHolder holder, final int position) {
        holder.removeRyder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    @Override
    public int getItemCount() {
        return ryders.size();
    }

}
