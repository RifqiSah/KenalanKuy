package com.alriftech.kenalankuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextView txtNIM, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtNIM = (TextView)findViewById(R.id.txtNIMLogin);
        txtPassword = (TextView)findViewById(R.id.txtPasswordLogin);

        txtNIM.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    public void Login(View v) {
        String NIM = txtNIM.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        cekUser(NIM, password);
    }

    public void cekUser(final String NIM, final String password) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child("users").child(NIM).exists()) {
                    User user = snapshot.child("users").child(NIM).getValue(User.class);
                    if (user.password.equals(password)){
                        Toast.makeText(Login.this, user.nama_lengkap + " telah berhasil masuk!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),Menu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(Login.this, "Password salah!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Akun tidak ditemukan! Silahkan mendaftar terlebih dahulu.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
