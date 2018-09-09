package com.alriftech.kenalankuy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Menu extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        scan_btn = (Button) findViewById(R.id.bt_search);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan QR Code Teman Anda");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setOrientationLocked(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setCaptureActivity(Search.class);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                getDetail(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getDetail(final String NIM){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child("users").child(NIM).exists()) {
                    User user = snapshot.child("users").child(NIM).getValue(User.class);
                    Toast.makeText(Menu.this, user.nama_lengkap + " telah berhasil ditemukan!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Menu.this, "Akun tidak ditemukan! Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
