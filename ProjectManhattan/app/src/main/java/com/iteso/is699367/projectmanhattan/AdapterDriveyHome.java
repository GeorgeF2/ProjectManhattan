package com.iteso.is699367.projectmanhattan;

import android.content.Context;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;
import java.util.List;


/*
*
* ADAPTER FOR HOME SCREEN OF THE ROLE DRIVEY
* IT USES THE SAME DATA SET AS THE ADRESSES IN THE APP
*
* */

public class AdapterDriveyHome extends RecyclerView.Adapter<AdapterDriveyHome.ViewHolder> {

    private ArrayList<Addresses> addresses;
    private Context context;

    public AdapterDriveyHome(Context context, ArrayList<Addresses> myDataSet) {
        addresses = myDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDriveyHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.addresses, viewGroup, false);
        AdapterDriveyHome.ViewHolder vh = new AdapterDriveyHome.ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout addressLayout;
        private Button addressGo, addressEdit;
        private TextView addressName, addressLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressLayout = itemView.findViewById(R.id.addresses_layout);
            addressGo = itemView.findViewById(R.id.addresses_go);
            addressEdit = itemView.findViewById(R.id.addresses_edit);
            addressName = itemView.findViewById(R.id.addresses_name);
            //addressLocation = itemView.findViewById(R.id.addre);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addressName.setText(addresses.get(position).getAddressName());
        holder.addressLocation.setText(addresses.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }
}
