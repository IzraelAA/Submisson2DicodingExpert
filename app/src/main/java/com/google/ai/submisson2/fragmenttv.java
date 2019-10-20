package com.google.ai.submisson2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragmenttv extends Fragment {
    private RecyclerView  rvMovie;
    private ArrayList<TV> list = new ArrayList<TV>();
    private String[]      dataName;
    private String[]      dataPhoto;
    private String[]      dataDiskripsi;
    private ListViewTv    movieadapter;
    ProgressDialog progressDialog;
    public fragmenttv() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup countainer,
                             Bundle savedInstanseState) {
        View view = inflater.inflate(R.layout.fragmenttv, countainer, false);
        rvMovie = view.findViewById(R.id.rv_movie1);
        rvMovie.setHasFixedSize(true);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieadapter = new ListViewTv(list);
        rvMovie.setAdapter(movieadapter);


        getData();
        return view;

    }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://api.themoviedb.org/3/tv/top_rated?api_key=122f9d8704d0b3196854f9fc7f2f975d&language=en-US&page=10";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    list = new ArrayList<TV>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject record = jsonArray.getJSONObject(i);
                        String     Id     = record.getString("overview");
                        String     Title  = record.getString("name");
                        String     Photo  = "https://image.tmdb.org/t/p/original"+record.getString("backdrop_path").trim();

                        TV data = new TV();

                        data.setNama(Title);
                        data.setDeskripsi(Id);
                        data.setPhoto(Photo);

                        list.add(data);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movieadapter.notifyDataSetChanged();

                movieadapter = new ListViewTv(list);
                rvMovie.setAdapter(movieadapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        queue.add(request);
    }
}
