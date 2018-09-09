package com.alriftech.kenalankuy;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void Login(View v) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }

    public void Register(View v) {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }
}
