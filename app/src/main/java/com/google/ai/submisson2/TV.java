package com.google.ai.submisson2;

import android.os.Parcel;
import android.os.Parcelable;

public class TV implements Parcelable {
    private String Nama;
    private String Deskripsi;
    private String photo;

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Nama);
        dest.writeString(this.Deskripsi);
        dest.writeString(this.photo);
    }

    public TV() {
    }

    protected TV(Parcel in) {
        this.Nama = in.readString();
        this.Deskripsi = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<TV> CREATOR = new Parcelable.Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel source) {
            return new TV(source);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };
}