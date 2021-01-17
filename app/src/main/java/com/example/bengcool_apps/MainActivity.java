package com.example.bengcool_apps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnlogout;
    Button btnservice;
    Button btnprofile;
    Button btnmap;
    Button btnchat;
    Button btncheckbook;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login session
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        currentUser = findViewById(R.id.tvCurrent);

        //currentUser.setText(mUser.getEmail());

        //declare tombol
        btnlogout = (Button) findViewById(R.id.buttonLogOut);
        btnservice = (Button) findViewById(R.id.buttonServices);
        btnprofile = (Button) findViewById(R.id.buttonProfile);
        btnmap = (Button) findViewById(R.id.buttonMap);
        btnchat = (Button) findViewById(R.id.buttonChat);
        btncheckbook = (Button) findViewById(R.id.buttonCheckBook);

        //ke page chat
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(MainActivity.this,Chat.class);
                startActivity(intent6);
            }
        });

        //ke page edit profile
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(MainActivity.this,EditProfile.class);
                startActivity(intent5);
            }
        });

        //ke page services
        btnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this,BookServices.class);
                startActivity(intent4);
            }
        });

        //ke page checkbooking
        btncheckbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(MainActivity.this,CheckBooking.class);
                startActivity(intent7);
            }
        });

        //ke page map
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=bengkel");
                Intent intent6 = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                intent6.setPackage("com.google.android.apps.maps");
                startActivity(intent6);
            }
        });

        //logout
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Logout Successfull", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();//logout
                Intent intent3 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent3);
            }
        });
    }

}
