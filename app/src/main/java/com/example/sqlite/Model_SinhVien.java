package com.example.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

public class Model_SinhVien implements Parcelable {
    public String hoten, ngaysinh, truong, sothich;
    public int gioitinh, id;

    public Model_SinhVien(String hoten, String ngaysinh, String truong, String sothich, int gioitinh, int id) {
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.truong = truong;
        this.sothich = sothich;
        this.gioitinh = gioitinh;
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }

    public String getSothich() {
        return sothich;
    }

    public void setSothich(String sothich) {
        this.sothich = sothich;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getHoten());
        dest.writeString(getNgaysinh());
        dest.writeString(getTruong());
        dest.writeString(getSothich());
        dest.writeInt(getGioitinh());
    }

    public Model_SinhVien(Parcel in) {
        this.id = in.readInt();
        this.hoten = in.readString();
        this.ngaysinh = in.readString();
        this.truong =in.readString();
        this.sothich = in.readString();
        this.gioitinh = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Model_SinhVien createFromParcel(Parcel in) {
            return new Model_SinhVien(in);
        }

        public Model_SinhVien[] newArray(int size) {
            return new Model_SinhVien[size];
        }
    };
}
