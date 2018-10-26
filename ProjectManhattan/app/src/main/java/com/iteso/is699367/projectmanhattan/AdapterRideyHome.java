package com.iteso.is699367.projectmanhattan;

import android.content.Context;
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
                inflate(R.layout.addresses, viewGroup, false);
        AdapterRideyHome.ViewHolder vh = new AdapterRideyHome.ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout addressLayout;
        private Button addressGo, addressEdit;
        private TextView addressName, addressLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressLayout = itemView.findViewById(R.id.addresses_layout);
            addressGo = itemView.findViewById(R.id.addresses_go_button);
            addressEdit = itemView.findViewById(R.id.addresses_edit_button);
            addressName = itemView.findViewById(R.id.addresses_name);
            //addressLocation = itemView.findViewById(R.id.addre);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRideyHome.ViewHolder holder, int position) {
        holder.addressName.setText(addresses.get(position).getAddressName());
        holder.addressLocation.setText(addresses.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }
}
