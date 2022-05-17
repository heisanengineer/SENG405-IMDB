package com.imdbListApp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdbListApp.Integrations.ImdbIntegration;
import com.imdbListApp.R;
import com.imdbListApp.adapters.MovieAdapter;
import com.imdbListApp.entities.DataResult;
import com.imdbListApp.entities.Movie;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Movie> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.movie_list);
        ImdbIntegration integration= new ImdbIntegration(this);
        Context context=this;
        integration.getMovies(new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                Log.d("Response",response);

                Type typeToken = new TypeToken<DataResult<List<Movie>>>() { }.getType();
                DataResult<List<Movie>> result= new Gson().fromJson(response,typeToken);
                mList=result.getResults();
                setList(result.getResults());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());
            }
        });

    }
    public void setList(List<Movie> movies){
        MovieAdapter adapter= new MovieAdapter(this,movies);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


}