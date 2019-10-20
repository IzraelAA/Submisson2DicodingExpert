package com.google.ai.regestrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    DbHelper database = new DbHelper(this);
Button btnRegristrasi;
EditText Name,Nohp;
TextInputLayout User,Pass,Email;
String Userr,passs,Emaila;

    private static final Pattern User1 =
            Pattern.compile("^"+
                    "(?=.*[a-zA-Z])" +
                    ".{6,20}" +
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.edtName);
        User = findViewById(R.id.edtUser1);
        Pass = findViewById(R.id.edtPassword1);
        Email = findViewById(R.id.edtEmail1);
        Nohp = findViewById(R.id.edtNohp);
        btnRegristrasi = findViewById(R.id.button2);
        btnRegristrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Userb()||Passb()||Email()){
                    database.insert(Name.getText().toString().trim(),User.getEditText().getText().toString().trim(),Pass.getEditText().getText().toString().trim(),Email.getEditText().getText().toString().trim(),Nohp.getText().toString().trim());

                    SharedPreferences    sp = getSharedPreferences("com.google.ai.regestrasi", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    Userr = User.getEditText().getText().toString().trim();
                    passs = Pass.getEditText().getText().toString().trim();
                    ed.putString("User", Userr);
                    ed.putString("Pass", passs);
                    ed.commit();

                    Intent in = new Intent(MainActivity.this,list.class);
                    startActivity(in);
                }
               else {
                    confirmInput();
                }
            }
        });
        blank();
    }

    private void blank() {
        Name.setText(null);
        User.getEditText().setText(null);
        Pass.getEditText().setText(null);
        Email.getEditText().setText(null);
        Nohp.setText(null);
    }
public boolean Email(){
    Emaila = Email.getEditText().getText().toString().trim();
    if (Emaila.isEmpty()){
        Email.setError("Jangan di kosongkan");
        return false;
    }  else if (!Patterns.EMAIL_ADDRESS.matcher(Emaila).matches()){
        Email.setError("Emal Salah");
        return false;
    }
        else{
        Email.setError(null);
        return true;
    }
}
    public boolean Userb(){
        Userr = User.getEditText().getText().toString().trim();
        passs = Pass.getEditText().getText().toString().trim();
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
        passs = Pass.getEditText().getText().toString().trim();
        if (passs.isEmpty()){
            Pass.setError("Jangan di kosongkan");
            return false;
        }else if (!User1.matcher(passs).matches()){
            Pass.setError("Password Lemah");
            return false;
        }
        else{
            Pass.setError(null);
            return true;
        }
    }
    public void confirmInput(){
        if (!Userb()|!Passb()|!Email()){
            return;
        }
        Userr = User.getEditText().getText().toString().trim();
        Userr +="\n" + Pass.getEditText().getText().toString().trim();
        Userr +="\n" + Email.getEditText().getText().toString().trim();
    }
}
