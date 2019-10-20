package com.google.ai.regestrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    ListView listView;
    private int waktu_loading = 3000;


    AlertDialog.Builder dialog;
    List<Data>              itemList =new ArrayList<Data>();
    public static final String Tag_user      = "user";
    public static final String Tag_sandi     = "sandi";
    DbHelper    database = new DbHelper(this);
TextView        signup;
TextInputLayout User,pass;
Button Login;
String Userr,passs;
private static final Pattern User1 =
Pattern.compile("^"+
        "(?=.*[a-zA-Z])" +
        ".{6,20}" +
        "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        User = findViewById(R.id.nameL);
        pass = findViewById(R.id.passL);
        signup = findViewById(R.id.sign);
        Login = findViewById(R.id.buttonlogin);
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences        sp   = getSharedPreferences("com.google.ai.regestrasi", MODE_PRIVATE);
                SharedPreferences.Editor ed   = sp.edit();
                Userr = User.getEditText().getText().toString().trim();
                passs = pass.getEditText().getText().toString().trim();
                ed.putString("User", Userr);
                ed.putString("Pass", passs);
                Boolean Login = database.Login(Userr,passs);
                if (Login == true){
                    ed.commit();
                    Intent intent = new Intent(Login.this,list.class);
                    startActivity(intent);
                }
                else  {
                    confirmInput();
                 Toast.makeText(getApplicationContext(),"Kata Username / Kata sandi Salah",Toast.LENGTH_SHORT);
                }

            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this,MainActivity.class);
                startActivity(in);
            }
        });



    }


    public boolean Userb(){
        Userr = User.getEditText().getText().toString().trim();
        passs = pass.getEditText().getText().toString().trim();
        if (Userr.isEmpty()){
            User.setError("Jangan di kosongkan");
            return false;
        }
        else{
            User.setError(null);
            return true;
        }
    }
    public boolean Passb(){
        Userr = User.getEditText().getText().toString().trim();
        passs = pass.getEditText().getText().toString().trim();
        if (passs.isEmpty()){
            pass.setError("Jangan di kosongkan");
            return false;
        }else if (!User1.matcher(passs).matches()){
            pass.setError("Password Lemah");
            return false;
        }
        else{
            pass.setError(null);
            return true;
        }
    }
    public void confirmInput(){
        if (!Userb()|!Passb()){
            return;
        }
        Userr = User.getEditText().getText().toString().trim();
        Userr +="\n" + pass.getEditText().getText().toString().trim();
    }
}
