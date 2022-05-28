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
    private static String API_URL="https://api.themoviedb.org/3/movie/top_rated?api_key=fcc7c50c104b7098c06f4d6539a88819&language=tr-TR&page=";
    private static String API_URL_CREDIT="https://api.themoviedb.org/3/movie/{movie}/credits?api_key=fcc7c50c104b7098c06f4d6539a88819&language=tr-TR";
    public static String BASE_POSTER_URL="https://image.tmdb.org/t/p/w342";
    private Context context;
    public  ImdbIntegration(Context context){
        this.context=context;
    }
    public void getMovies(int page,Response.Listener<String> listener,Response.ErrorListener errorListener){
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL + page, listener, errorListener);
            queue.add(stringRequest);
        }catch (Exception e){
            Log.d("API",e.getMessage());

        }
    }
    public void getCredits(long movieId,Response.Listener<String> listener,Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL_CREDIT.replace("{movie}",movieId+""),listener,errorListener);
        queue.add(stringRequest);
    }
}
