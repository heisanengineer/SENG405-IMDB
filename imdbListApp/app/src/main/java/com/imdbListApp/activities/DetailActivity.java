package com.imdbListApp.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdbListApp.Integrations.ImdbIntegration;
import com.imdbListApp.R;
import com.imdbListApp.adapters.ActorAdapter;
import com.imdbListApp.adapters.MovieAdapter;
import com.imdbListApp.entities.Actor;
import com.imdbListApp.entities.CastResult;
import com.imdbListApp.entities.DataResult;
import com.imdbListApp.entities.Movie;
import com.squareup.picasso.Picasso;
import java.lang.reflect.Type;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemBars();
        setContentView(R.layout.activity_detail);
        Intent intent= getIntent();
        String movieJson=intent.getStringExtra("movie");

        Log.d("Response",movieJson);

        Type typeToken = new TypeToken<Movie>() { }.getType();
        Movie movie= new Gson().fromJson(movieJson,typeToken);
        TextView header = (TextView) findViewById(R.id.detail_title);
        TextView imdbavg = (TextView) findViewById(R.id.detail_avg);
        TextView content = (TextView) findViewById(R.id.detail_content);
        TextView date = (TextView) findViewById(R.id.detail_date);
        TextView lang = (TextView) findViewById(R.id.detail_lang);
        recyclerView=(RecyclerView) findViewById(R.id.actorview);
        header.setText(movie.title);
        imdbavg.setText(String.valueOf(movie.vote_average));
        content.setText(movie.overview);
        date.setText(movie.release_date);
        lang.setText(movie.original_language);
        ImageView image = (ImageView) findViewById(R.id.detail_image);
        Picasso.get().load(ImdbIntegration.BASE_POSTER_URL+movie.poster_path).into(image);
        getCredits(movie.id);
        Button button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }
    private void getCredits(long id){
        ImdbIntegration integration= new ImdbIntegration(this);
        Context context=this;
        integration.getCredits(id,new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                Log.d("Response",response);
                CastResult result= new Gson().fromJson(response,CastResult.class);
                setList(result.cast);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }
    public void setList(List<Actor> actors){
        ActorAdapter adapter= new ActorAdapter(this,actors);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}