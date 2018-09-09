package com.alriftech.kenalankuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
    }

    public void myqr(View v){
        Intent i = new Intent(getApplicationContext(), MyQR.class);
        startActivity(i);
    }
}
