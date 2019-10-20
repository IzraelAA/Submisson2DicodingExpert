package com.google.ai.regestrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {

    private int waktu_loading = 3000;
    DbHelper database = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override


            public void run() {
                SharedPreferences sp = getSharedPreferences("com.google.ai.regestrasi", MODE_PRIVATE);

                String Username = sp.getString("User", "");
                String password = sp.getString("Pass", "");
                if (Username.equals("")||password.equals("")) {
                    Intent intent = new Intent(splashscreen.this, Login.class);
                    startActivity(intent);
                } else {
                    Intent in = new Intent(splashscreen.this,list.class);
                    startActivity(in);
                }
            }
        }, waktu_loading);
    }
}
