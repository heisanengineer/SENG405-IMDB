package com.imdbListApp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent= getIntent();
        String movieJson=intent.getStringExtra("movie");

        Log.d("Response",movieJson);

        Type typeToken = new TypeToken<Movie>() { }.getType();
        Movie movie= new Gson().fromJson(movieJson,typeToken);
        TextView header = (TextView) findViewById(R.id.detail_title);
        header.setText(movie.title);


    }

}