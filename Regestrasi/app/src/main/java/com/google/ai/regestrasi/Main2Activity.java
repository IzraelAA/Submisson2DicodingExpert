package com.google.ai.regestrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView name,user,email,nohp;
    String names,users,emails,nohps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        user = findViewById(R.id.textView);
        email = findViewById(R.id.textView3);
        nohp = findViewById(R.id.nohpa);
        names = getIntent().getStringExtra(list.Tag_name);
        users = getIntent().getStringExtra(list.Tag_user);
        emails = getIntent().getStringExtra(list.Tag_email);
        nohps = getIntent().getStringExtra(list.Tag_nohp);

        name.setText(names);
        user.setText(users);
        email.setText(emails);
        nohp.setText(nohps);
    }
}
