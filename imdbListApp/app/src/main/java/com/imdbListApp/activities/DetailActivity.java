package com.imdbListApp.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdbListApp.Integrations.ImdbIntegration;
import com.imdbListApp.R;
import com.imdbListApp.entities.Movie;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class DetailActivity extends AppCompatActivity {

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
        header.setText(movie.title);
        imdbavg.setText(String.valueOf(movie.vote_average));
        content.setText(movie.overview);
        ImageView image = (ImageView) findViewById(R.id.detail_image);
        Picasso.get().load(ImdbIntegration.BASE_POSTER_URL+movie.poster_path).into(image);

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

}