package com.example.bengcool_apps;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    Thread background = new Thread() {
        public void run() {

            try {
                // Thread will sleep for 5 seconds
                sleep(2*1000);

                Intent intent = new Intent(SplashScreen.this,SignUpActivity.class);
                startActivity(intent);


                // After 5 seconds redirect to another intent


                //Remove activity
                finish();

            } catch (Exception e) {

            }
        }
    };

    // start thread
        background.start();


}
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
