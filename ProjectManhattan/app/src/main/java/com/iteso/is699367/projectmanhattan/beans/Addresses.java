package com.iteso.is699367.projectmanhattan.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Addresses implements Parcelable {

    private String addressName;
    private String street;
    private String city;
    private String state;

    public Addresses() {
    }

    public Addresses(String addressName, String street, String city, String state) {
        this.addressName = addressName;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    protected Addresses(Parcel in) {
        addressName = in.readString();
        street = in.readString();
        city = in.readString();
        state = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressName);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return street + " " + city + " " + state;
    }

    public void setAddress(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "addressName='" + addressName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
