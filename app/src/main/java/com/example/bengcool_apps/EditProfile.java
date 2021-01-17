package com.example.bengcool_apps;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference ref;
    String password1 = "";
    String password2= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        ref = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Button btnSave = (Button) findViewById(R.id.btnOK);
        String email = mUser.getEmail();

        final EditText etName = (EditText) findViewById(R.id.editText);
        EditText etEmail = (EditText) findViewById(R.id.editText2);

        etEmail.setText(email);
        etEmail.setEnabled(false);


        ref.child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                //final List<String> booking = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
//                    if(areaSnapshot.child("emailCust").getValue(String.class) == mUser.getEmail()){
//                        String bengkel = areaSnapshot.child("companyName").getValue(String.class);
//                        String tipe = areaSnapshot.child("serviceType").getValue(String.class);
//                        booking.add(bengkel+" - "+tipe);
//                    }
                    if(areaSnapshot.child("email").getValue(String.class).equals(mUser.getEmail())){
                        etName.setText(areaSnapshot.child("name").getValue(String.class));
                        password1 = areaSnapshot.child("password1").getValue(String.class);
                        password2 = areaSnapshot.child("password2").getValue(String.class);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newName = etName.getText().toString();

                Query query = ref.orderByChild("Member").equalTo(mUser.getEmail());

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
//                    if(areaSnapshot.child("emailCust").getValue(String.class) == mUser.getEmail()){
//                        String bengkel = areaSnapshot.child("companyName").getValue(String.class);
//                        String tipe = areaSnapshot.child("serviceType").getValue(String.class);
//                        booking.add(bengkel+" - "+tipe);
//                    }
                            String email = areaSnapshot.child("email").getValue(String.class);
                            if(email.equals(mUser.getEmail())){
                                areaSnapshot.getRef().child("name").setValue(newName);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(), "Name Updated!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfile.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
