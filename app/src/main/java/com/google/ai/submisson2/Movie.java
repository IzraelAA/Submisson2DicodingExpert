package com.google.ai.submisson2;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
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

    private String Nama;
    private String Deskripsi;
    private String photo;

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

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.Nama = in.readString();
        this.Deskripsi = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
