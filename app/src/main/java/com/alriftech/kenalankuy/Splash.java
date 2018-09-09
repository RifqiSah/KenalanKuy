package com.alriftech.kenalankuy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgress = (ProgressBar) findViewById(R.id.prog);
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(100);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
//                Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
    }
}
