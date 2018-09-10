package com.alriftech.kenalankuy;

public class Teman {
    public String NIM;
    public boolean status;

    public Teman() {
        // Default
    }

    public Teman(String NIM, boolean status) {
        this.NIM = NIM;
        this.status = status;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
