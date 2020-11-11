package com.wjf.aidlserver.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPerson implements Parcelable {
    private String name = "";
    private int age = 0;
    private int height = 0;
    private String address = "";
    private boolean isMarry = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMarry() {
        return isMarry;
    }

    public void setMarry(boolean marry) {
        isMarry = marry;
    }

    protected MyPerson(Parcel in) {
        parcel(in);
    }

    private void parcel(Parcel in) {
        in.readString();
        in.readInt();
        in.readInt();
        in.readString();
        in.readString();
        in.readBoolean();
    }

    public MyPerson() {

    }

    public void readFromParcel(Parcel dest) {
        parcel(dest);
    }

    public static final Creator<MyPerson> CREATOR = new Creator<MyPerson>() {
        @Override
        public MyPerson createFromParcel(Parcel in) {
            return new MyPerson(in);
        }

        @Override
        public MyPerson[] newArray(int size) {
            return new MyPerson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(height);
        dest.writeString(address);
        dest.writeBoolean(isMarry);
    }
}
