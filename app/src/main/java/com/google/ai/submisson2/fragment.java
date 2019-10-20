package com.google.ai.submisson2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class fragment extends Fragment {
    private RecyclerView rvMovie;
    private ArrayList<Movie> list = new ArrayList<Movie>();
    private String[] dataName;
    private String[] dataPhoto;
    private String[] dataDiskripsi;
    private  ListViewMovie movieadapter;
    public fragment(){

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup countainer,
                              Bundle savedInstanseState) {
        View view = inflater.inflate(R.layout.fragment_item_list, countainer, false);

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieadapter = new ListViewMovie(list);
        rvMovie.setAdapter(movieadapter);

        getListData();
        return view; }
    private void getListData(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=122f9d8704d0b3196854f9fc7f2f975d&language=en-US&page=10";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    list = new ArrayList<Movie>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject record = jsonArray.getJSONObject(i);
                        String     Id     = record.getString("overview");
                        String     Title  = record.getString("title");
                        String     Photo  = "https://image.tmdb.org/t/p/original"+record.getString("poster_path").trim();


                        Movie movie = new Movie();
                        movie.setPhoto(Photo);
                        movie.setNama(Title);
                        movie.setDeskripsi(Id);
                        list.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movieadapter.notifyDataSetChanged();

                movieadapter = new ListViewMovie(list);
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