package com.iteso.is699367.projectmanhattan.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.is699367.projectmanhattan.ActivityStart;
import com.iteso.is699367.projectmanhattan.ActivityWait;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class AdapterRideyHome extends RecyclerView.Adapter<AdapterRideyHome.ViewHolder> {

    private ArrayList<Addresses> addresses;
    private Context context;


    public AdapterRideyHome(Context context, ArrayList<Addresses> myDataSet) {
        addresses = myDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRideyHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.addresses_ridey, viewGroup, false);
        AdapterRideyHome.ViewHolder vh = new AdapterRideyHome.ViewHolder(v);
        return vh;
    }
    private static String rydeId;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout addressLayout;
        private Button addressGo;
        private TextView addressName, addressLocation, addressTime;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressLayout = itemView.findViewById(R.id.addresses_ridey_layout);
            addressGo = itemView.findViewById(R.id.addresses_ridey_go_button);
            addressName = itemView.findViewById(R.id.addresses_ridey_name);
            addressLocation = itemView.findViewById(R.id.addresses_ridey_address);
            addressTime = itemView.findViewById(R.id.addresses_ridey_time);


            FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.child("Users").getChildren()){
                        if (snapshot.child("name").getValue().toString() == addressName.getText().toString()){
                            rydeId = snapshot.getKey();
                        }

                    }
                    addressTime.setText(dataSnapshot.child("Rydes").child(rydeId).child("time").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRideyHome.ViewHolder holder, final int position) {
        holder.addressGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getin();
            }
        });
        holder.addressName.setText(addresses.get(position).getAddressName());
        holder.addressLocation.setText(addresses.get(position).getAddress());
    }
    public void getin(){
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        Intent intent = new Intent(context, ActivityWait.class);
        intent.putExtra("rydeid", rydeId);
        context.startActivity(intent);


        FirebaseDatabase.getInstance().getReference().child("Rydes").child(rydeId).child("Ryders").child(id).child("name").setValue(name);
        FirebaseDatabase.getInstance().getReference().child("Rydes").child(rydeId).child("Ryders").child(id).child("rating").setValue("4");
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }
}
