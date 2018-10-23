package com.iteso.is699367.projectmanhattan.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Addresses implements Parcelable {

    private String addressName, address, addressImage;

    public Addresses() {
    }

    protected Addresses(Parcel in) {
        addressName = in.readString();
        address = in.readString();
        addressImage = in.readString();
    }

    public static final Creator<Addresses> CREATOR = new Creator<Addresses>() {
        @Override
        public Addresses createFromParcel(Parcel in) {
            return new Addresses(in);
        }

        @Override
        public Addresses[] newArray(int size) {
            return new Addresses[size];
        }
    };

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressImage() {
        return addressImage;
    }

    public void setAddressImage(String addressImage) {
        this.addressImage = addressImage;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressName);
        dest.writeString(address);
        dest.writeString(addressImage);
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "addressName='" + addressName + '\'' +
                ", address='" + address + '\'' +
                ", addressImage='" + addressImage + '\'' +
                '}';
    }
}
