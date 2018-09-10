package com.alriftech.kenalankuy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MyQR extends AppCompatActivity {
    ImageView image;
    TextView txtNIM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_qr);
        image = (ImageView) findViewById(R.id.image);
        txtNIM = (TextView)findViewById(R.id.txtNimQR);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        image.setImageBitmap(bmp);
        txtNIM.setText(getIntent().getExtras().getString("NIM"));
    }
}
