package com.example.bengcool_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckBooking extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_booking);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

// ...
        mDatabase = FirebaseDatabase.getInstance();

        DatabaseReference ref = mDatabase.getReference();

        ref.child("Booking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> booking = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
//                    if(areaSnapshot.child("emailCust").getValue(String.class) == mUser.getEmail()){
//                        String bengkel = areaSnapshot.child("companyName").getValue(String.class);
//                        String tipe = areaSnapshot.child("serviceType").getValue(String.class);
//                        booking.add(bengkel+" - "+tipe);
//                    }
                    String tipe = areaSnapshot.child("serviceType").getValue(String.class);
                    String bengkel = areaSnapshot.child("companyName").getValue(String.class);
                    String email = areaSnapshot.child("emailCust").getValue(String.class);
                    if(email.equals(mUser.getEmail())){
                        booking.add(bengkel+" - "+tipe);
                    }

                }

                ListView list = (ListView) findViewById(R.id.listBooking);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(CheckBooking.this, android.R.layout.simple_list_item_1, booking);
                list.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
