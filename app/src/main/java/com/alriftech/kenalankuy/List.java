package com.alriftech.kenalankuy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class List extends AppCompatActivity {

    private ListView lstTeman;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lstTeman =  (ListView)findViewById(R.id.lstTeman);
        lstTeman.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);
                String NIM = value.substring(0, 9);

                // Toast.makeText(List.this, NIM, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), Profil.class);
                i.putExtra("NIM", NIM);
                startActivity(i);
            }
        });

        final Session globalVariable = (Session)getApplicationContext();
        final String NIM  = globalVariable.getNIM();

        loadFriend(NIM);
    }

    private void loadFriend(final String NIM) {
         final ArrayAdapter<String> adapter;
         final ArrayList<String> arrayList;

         arrayList = new ArrayList<String>();
         adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.blacktext, arrayList);

         lstTeman.setAdapter(adapter);

         DatabaseReference database = FirebaseDatabase.getInstance().getReference();
         database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.child("users").child(NIM).child("friends").getChildren()) {
                    final String key = ds.getKey();

//                    arrayList.add(key);
//                    adapter.notifyDataSetChanged();

                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.child("users").child(key).exists()) {
                                User user = snapshot.child("users").child(key).getValue(User.class);

                                arrayList.add(key + " - " + user.nama_lengkap);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
