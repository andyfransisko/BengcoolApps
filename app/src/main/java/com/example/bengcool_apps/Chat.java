package com.example.bengcool_apps;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class Chat extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference reff = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference refff = mDatabase.getReference();

        refff.child("Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> chatm = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {

                    String email = areaSnapshot.child("email").getValue(String.class);
                    String pesan = areaSnapshot.child("messageText").getValue(String.class);
                    if(email.equals(mUser.getEmail())){
                        chatm.add(pesan);
                    }

                }

                ListView list = (ListView) findViewById(R.id.listChat);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Chat.this, android.R.layout.simple_list_item_1, chatm);
                list.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnsend = findViewById(R.id.btnsend);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText myEditText = findViewById(R.id.txtinput);
                String mesage = myEditText.getText().toString().trim();
                myEditText.setText("");
                String email = mUser.getEmail();

                ChatMessages cm = new ChatMessages(email, mesage);
                reff.child("Chat").push().setValue(cm);
            }
        });

    }

}
