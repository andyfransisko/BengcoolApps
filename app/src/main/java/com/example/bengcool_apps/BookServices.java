package com.example.bengcool_apps;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BookServices extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_services);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Button btnBook = (Button) findViewById(R.id.btnBook);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner bengkel = (Spinner) findViewById(R.id.idbengkel);
                Spinner servis = (Spinner) findViewById(R.id.idservis);
                String name = bengkel.getSelectedItem().toString();
                String type = servis.getSelectedItem().toString();

                String email = mUser.getEmail();

                Service service = new Service(email, name, type);
                ref.child("Booking").push().setValue(service);
                Toast.makeText(getApplicationContext(), "Service Booked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
