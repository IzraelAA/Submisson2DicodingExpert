package com.google.ai.submisson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detai_activity extends AppCompatActivity {


    public  static final String IMG_Movie = "wisata_photo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_activity);

        ImageView imgPhoto =findViewById(R.id.img_item_photo1);
        TextView  tvName   = findViewById(R.id.tv_item_name1);
        TextView  tvDesc   = findViewById(R.id.tv_item_from1);

        Bundle bundle = getIntent().getExtras();

        Movie film =  bundle.getParcelable(IMG_Movie);


        tvName.setText(film.getNama());
        tvDesc.setText(film.getDeskripsi());
        Glide.with(this).load(film.getPhoto()).into(imgPhoto);

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(film.getNama());
        }
    }
}
