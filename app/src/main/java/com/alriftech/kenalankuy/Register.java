package com.alriftech.kenalankuy;

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

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    TextView txtNIM, txtNama, txtKota, txtPassword;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNIM = (TextView)findViewById(R.id.txtNIM);
        txtNama = (TextView)findViewById(R.id.txtNama);
        txtKota = (TextView)findViewById(R.id.txtAsal);
        txtPassword = (TextView)findViewById(R.id.txtPassword);

        txtNIM.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    public void DaftarAkun(View v) {
        final String NIM = txtNIM.getText().toString().trim();
        final String nama = txtNama.getText().toString().trim();
        final String kota = txtKota.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        if (NIM.equals("")) {
            txtNIM.setError( "Harap isi NIM Anda terlebih dahulu!" );
            return;
        }

        if (nama.equals("")) {
            txtNama.setError( "Harap isi nama lengkap Anda terlebih dahulu!" );
            return;
        }

        if (kota.equals("")) {
            txtKota.setError( "Harap isi kota asal Anda terlebih dahulu!" );
            return;
        }

        if (password.equals("")) {
            txtPassword.setError( "Harap isi password Anda terlebih dahulu!" );
            return;
        }

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child("users").child(NIM).exists()) {
//                    User user = snapshot.child(NIM).getValue(User.class);
//                    Toast.makeText(Register.this, user.getNama_lengkap(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Register.this, NIM + " telah terdaftar, silahkan login untuk memulai sesi!", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(nama, kota, password);
                    database.child("users").child(NIM).setValue(user);

                    Toast.makeText(Register.this, NIM + " berhasil didaftarkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
