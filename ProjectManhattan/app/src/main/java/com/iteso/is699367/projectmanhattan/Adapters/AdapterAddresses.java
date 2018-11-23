package com.iteso.is699367.projectmanhattan.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.iteso.is699367.projectmanhattan.R;
import com.iteso.is699367.projectmanhattan.beans.Addresses;

import java.util.ArrayList;

public class AdapterAddresses extends RecyclerView.Adapter<AdapterAddresses.ViewHolder>{

    private ArrayList<Addresses> addresses;
    private Context context;

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

                Addresses addr = addresses.get(position);
                addresses.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, addresses.size());

            }
        });
        holder.addressName.setText(addresses.get(position).getAddressName());
        holder.addressLocation.setText(addresses.get(position).getAddress());
        holder.addressPicture.setImageResource(R.drawable.simple_house);
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

}
