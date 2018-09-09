package com.alriftech.kenalankuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Search(View v){
        Intent i = new Intent(getApplicationContext(), Search.class);
        startActivity(i);
    }

    public void List(View v){
        Intent i = new Intent(getApplicationContext(), List.class);
        startActivity(i);
    }

    public void Profil(View v){
        Intent i = new Intent(getApplicationContext(), Profil.class);
        startActivity(i);
    }
}
