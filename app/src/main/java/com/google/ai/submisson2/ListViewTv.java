package com.google.ai.submisson2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListViewTv extends RecyclerView.Adapter<ListViewTv.ListViewHolder> {
    private ArrayList<TV> movieArrayList;

    RequestOptions options;

    public ListViewTv(ArrayList<TV> list) {
        this.movieArrayList = list;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list1, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final TV m = movieArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(m.getPhoto())
                .apply(options.override(55, 55))
                .into(holder.imgPhoto);
        holder.tvNama.setText(m.getNama());
        holder.tvDeskripsi.setText(m.getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Detail_activity.IMG_Movie, m);
                Intent moveWithDataIntent = new Intent(view.getContext(), Detail_activity.class);
                moveWithDataIntent.putExtras(bundle);
                view.getContext().startActivity(moveWithDataIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(movieArrayList == null){
            return 0;
        }else{
        return movieArrayList.size();
    }}

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;
        public TextView  tvNama;
        public TextView  tvDeskripsi;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo1);
            tvNama = itemView.findViewById(R.id.tv_item_name1);
            tvDeskripsi = itemView.findViewById(R.id.tv_item_from1);
        }
    }
}