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
import com.iteso.is699367.projectmanhattan.ActivityLogin;
import com.iteso.is699367.projectmanhattan.ActivitySignIn;
import com.iteso.is699367.projectmanhattan.Fragments.FragmentAddresses;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class AdapterAddresses extends RecyclerView.Adapter<AdapterAddresses.ViewHolder>{

    private ArrayList<Addresses> addresses;
    private Context context;
    DatabaseReference myRef;
    FirebaseUser user;
    String delId = "placeholder";

    public AdapterAddresses(Context context, ArrayList<Addresses> myDataSet) {
        addresses = myDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAddresses.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.addresses_tab, viewGroup, false);
        AdapterAddresses.ViewHolder vh = new AdapterAddresses.ViewHolder(v);

        user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Addresses");


        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout addressLayout;
        private Button addressDelete, addressEdit;
        private TextView addressName, addressLocation;
        private ImageView addressPicture;

        public ViewHolder(View v) {
            super(v);
            addressLayout = itemView.findViewById(R.id.addresses_tab_layout);
            addressEdit = itemView.findViewById(R.id.addresses_tab_edit_button);
            addressDelete = itemView.findViewById(R.id.addresses_tab_delete_button);
            addressName = itemView.findViewById(R.id.addresses_name);
            addressLocation = itemView.findViewById(R.id.addresses_address);
            addressPicture = itemView.findViewById(R.id.addresses_pictures);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddresses.ViewHolder holder, final int position) {
        holder.addressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if (snapshot.child("addressName").getValue().toString().contains(addresses.get(position).getAddressName())){
                                delId = snapshot.getKey();
                                myRef.child(delId).setValue(null);
                                delId = "placeholder";

                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                myRef.child(delId).setValue(null);

            }
        });

        holder.addressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if (snapshot.child("addressName").getValue().toString().contains(addresses.get(position).getAddressName())){
                                String addrId = snapshot.getKey();
                                //send addrId trough intent
                                Intent intent = new Intent(context, ActivityEditAddress.class);
                                intent.putExtra("ADDRID", addrId);
                                context.startActivity(intent);
                            }
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });
        holder.addressName.setText(addresses.get(position).getAddressName());
        holder.addressLocation.setText(addresses.get(position).getAddress());
//        holder.addressPicture.setImageResource(R.drawable.simple_house);
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

}
