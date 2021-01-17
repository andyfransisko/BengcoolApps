package com.example.bengcool_apps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    //declare variable log in
    EditText inEmail,inPass;
    Button btnlog;
    DatabaseReference reff;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Page Sign Up
        TextView btn1;
        btn1 = (TextView) findViewById(R.id.btnSignUp);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent1);
            }
        });

        //untuk login
        inEmail = findViewById(R.id.idEmail);
        inPass = findViewById(R.id.idPassword);
        btnlog = (Button) findViewById(R.id.btnLOG);
        fAuth = FirebaseAuth.getInstance();

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inEmail.getText().toString().trim();
                String pass = inPass.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    inEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    inPass.setError("Password is required");
                    return;
                }

                if(pass.length()<8)
                {
                    inPass.setError("Password must 8 or more character");
                    return;
                }

                //progress.setVisibility(View.VISIBLE);

                //authenticate user
                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progress.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Login Successfull", Toast.LENGTH_LONG).show();

                            //pindah ke halaman utama
                            Intent intent3 = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent3);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Wrong Email and Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
