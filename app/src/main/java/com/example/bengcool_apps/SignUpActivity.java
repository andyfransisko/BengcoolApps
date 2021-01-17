package com.example.bengcool_apps;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    //Declare Variable Sign Up
    EditText txtNama,txtEmail,txtPass1,txtPass2;
    Button btnsave;
    CheckBox checkBox;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    InsertMember member;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Page Login
        TextView btn1;
        btn1 = (TextView) findViewById(R.id.btnLogIn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });

        //Form SignUp
        txtNama = (EditText) findViewById(R.id.idNama);
        txtEmail = (EditText) findViewById(R.id.idEmail);
        txtPass1 = (EditText) findViewById(R.id.idPass1);
        txtPass2 = (EditText) findViewById(R.id.idPass2);
        btnsave = (Button) findViewById(R.id.btnSignUp);
        progress = findViewById(R.id.progress1);

        member = new InsertMember();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null)
        {

        }

        //Insert Data
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setText to InsertMember
                member.setName(txtNama.getText().toString().trim());
                member.setEmail(txtEmail.getText().toString().trim());
                member.setPassword1(txtPass1.getText().toString().trim());
                member.setPassword2(txtPass2.getText().toString().trim());

                String password2 = txtPass2.getText().toString().trim();
                String nama = txtNama.getText().toString().trim();

                //untuk database auth
                String email = txtEmail.getText().toString().trim();
                String password1 = txtPass1.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    txtEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password1))
                {
                    txtPass1.setError("Password is required");
                    return;
                }

                if(TextUtils.isEmpty(password2))
                {
                    txtPass2.setError("Password is required");
                    return;
                }

                if(TextUtils.isEmpty(nama))
                {
                    txtNama.setError("Name is required");
                    return;
                }

                if(!password1.equals(password2))
                {
                    txtPass2.setError("Password does not match");
                    return;
                }

                if(password1.length()<8)
                {
                    txtPass1.setError("Password must 8 or more character");
                    return;
                }

                if(password2.length()<8)
                {
                    txtPass2.setError("Password must 8 or more character");
                    return;
                }

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignUpActivity.this,"Enter All Fields", Toast.LENGTH_LONG).show();
                }
                progress.setVisibility(View.VISIBLE);

                //register auth in firebase
                fAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            //progress.setVisibility(View.VISIBLE);
                            Toast.makeText(SignUpActivity.this,"Sign Up Successfull", Toast.LENGTH_LONG).show();
                            //insert to realtime database
                            reff.push().setValue(member);

                            //pindah ke halaman login
                            Intent intent3 = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent3);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

}
