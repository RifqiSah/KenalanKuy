package com.alriftech.kenalankuy;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class Profil extends AppCompatActivity {
    TextView nim, nama, asal;
    Button gen_btn;
    ImageView image;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nim = (TextView) findViewById(R.id.et_nim);
        nama = (TextView) findViewById(R.id.et_nama);
        asal = (TextView) findViewById(R.id.et_asal);

        gen_btn = (Button) findViewById(R.id.bt_qr);
        image = (ImageView) findViewById(R.id.image);

        if( getIntent().getExtras() != null) {
            loadDataIntent();
            gen_btn.setVisibility(View.INVISIBLE);
        }
        else {
            loadDataSession();
            gen_btn.setVisibility(View.VISIBLE);
        }

        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text2Qr = nim.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                    image.setImageBitmap(bitmap);
//                    Convert into byte array before added to intent
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent i = new Intent(getApplicationContext(), MyQR.class);
                    i.putExtra("NIM", nim.getText());
                    i.putExtra("image", byteArray);
                    startActivity(i);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if( getIntent().getExtras() != null) {
            loadDataIntent();
            gen_btn.setVisibility(View.INVISIBLE);
        }
        else {
            loadDataSession();
            gen_btn.setVisibility(View.VISIBLE);
        }
    }

    private void loadDataSession() {
        final Session globalVariable = (Session)getApplicationContext();
        final String NIM  = globalVariable.getNIM();

        nim.setText(NIM);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child("users").child(NIM).exists()) {
                    User user = snapshot.child("users").child(NIM).getValue(User.class);

                    nama.setText(user.nama_lengkap);
                    asal.setText(user.kota);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void loadDataIntent() {
        final String NIM = getIntent().getExtras().getString("NIM");
        nim.setText(NIM);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child("users").child(NIM).exists()) {
                    User user = snapshot.child("users").child(NIM).getValue(User.class);

                    nama.setText(user.nama_lengkap);
                    asal.setText(user.kota);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
