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
public class ListViewMovie extends RecyclerView.Adapter<ListViewMovie.ListViewHolder> {
    private ArrayList<Movie> movieArrayList;

    public ListViewMovie(ArrayList<Movie> list){
        this.movieArrayList = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Movie m = movieArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(m.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);
        holder.tvNama.setText(m.getNama());
        holder.tvDeskripsi.setText(m.getDeskripsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Detai_activity.IMG_Movie, m);
                Intent moveWithDataIntent = new Intent(view.getContext(), Detai_activity.class);
                moveWithDataIntent.putExtras(bundle);
                view.getContext().startActivity(moveWithDataIntent);    }
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;
        public     TextView  tvNama;
        public     TextView  tvDeskripsi;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvNama = itemView.findViewById(R.id.tv_item_name);
            tvDeskripsi = itemView.findViewById(R.id.tv_item_from);
        }
    }

}
