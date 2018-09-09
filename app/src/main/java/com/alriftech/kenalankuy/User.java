package com.alriftech.kenalankuy;

public class User {
//    public String NIM;
    public String nama_lengkap;
    public String kota;
    public String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nama_lengkap, String kota, String password) {
//        this.NIM            = NIM;
        this.nama_lengkap   = nama_lengkap;
        this.kota           = kota;
        this.password       = password;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
