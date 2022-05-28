package com.imdbListApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.imdbListApp.Integrations.ImdbIntegration;
import com.imdbListApp.R;
import com.imdbListApp.activities.DetailActivity;
import com.imdbListApp.entities.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {
    List<Movie> movieList;
    LayoutInflater inflater;
    Context _context;
    public MovieAdapter(Context context,List<Movie> movies){
        inflater= LayoutInflater.from(context);
        movieList=movies;
        _context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.movie_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie selectedMovie = movieList.get(position);
        holder.SetData(selectedMovie, position);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        Movie movie;
        TextView header, content;
        ImageView image;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header=(TextView) itemView.findViewById(R.id.header);
            content=(TextView) itemView.findViewById(R.id.content);
            image=(ImageView) itemView.findViewById(R.id.image);
            button=(Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movie=movieList.get(getPosition());
                    Log.d("tıklanan",new Gson().toJson(movie));
                    Intent intent = new Intent(_context, DetailActivity.class);
                    intent.putExtra("movie",new Gson().toJson(movie));
                    _context.startActivity(intent);
                }
            });
        }

        public void SetData(Movie movie,int position){
            this.header.setText(movie.title);
            this.content.setText(movie.overview);
            this.content.setText(movie.vote_average+"");
            Picasso.get().load(ImdbIntegration.BASE_POSTER_URL+movie.poster_path).into(image);
            this.movie=movie;
        }
    }
}
