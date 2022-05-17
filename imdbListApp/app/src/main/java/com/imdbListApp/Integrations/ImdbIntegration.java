package com.imdbListApp.Integrations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImdbIntegration {
    private static String API_URL="https://api.themoviedb.org/3/movie/top_rated?api_key=fcc7c50c104b7098c06f4d6539a88819&language=tr-TR&page=1\n\n";
    public static String BASE_POSTER_URL="https://image.tmdb.org/t/p/w342";
    private Context context;
    public  ImdbIntegration(Context context){
        this.context=context;
    }
    public void getMovies(Response.Listener<String> listener,Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,listener,errorListener);
        queue.add(stringRequest);
    }
}
