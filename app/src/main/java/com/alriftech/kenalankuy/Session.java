package com.alriftech.kenalankuy;

import android.app.Application;

public class Session extends Application {
    private String NIM = "";

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String aNIM) {
        NIM = aNIM;
    }
}
