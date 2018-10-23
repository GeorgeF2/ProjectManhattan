package com.iteso.is699367.projectmanhattan.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name, college, carModel, profilePicture;
    private boolean hasCar;

    public User(String name, String college, String carModel, String profilePicture, boolean hasCar) {
        this.name = name;
        this.college = college;
        if(hasCar) {
            this.carModel = carModel;
        }
        else {
            this.carModel = "None";
        }
        this.profilePicture = profilePicture;
        this.hasCar = hasCar;
    }

    protected User(Parcel in) {
        name = in.readString();
        college = in.readString();
        carModel = in.readString();
        profilePicture = in.readString();
        hasCar = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(college);
        dest.writeString(carModel);
        dest.writeString(profilePicture);
        dest.writeByte((byte) (hasCar ? 1 : 0));
    }
}
