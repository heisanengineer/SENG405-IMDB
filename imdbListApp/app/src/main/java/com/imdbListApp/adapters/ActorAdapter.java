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
import com.imdbListApp.entities.Actor;
import com.imdbListApp.entities.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>  {
    List<Actor> actorList;
    LayoutInflater inflater;
    Context _context;
    public ActorAdapter(Context context, List<Actor> actors){
        inflater= LayoutInflater.from(context);
        actorList=actors;
        _context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.actor_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Actor selectedActor = actorList.get(position);
        holder.SetData(selectedActor, position);
    }

    @Override
    public int getItemCount() {
        return this.actorList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        Actor actor;
        ImageView profile;
        TextView realname, character;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile=(ImageView) itemView.findViewById(R.id.profile);
            realname=(TextView) itemView.findViewById(R.id.realname);
            character=(TextView) itemView.findViewById(R.id.character);
        }

        public void SetData(Actor actor,int position){
            this.realname.setText(actor.name);
            this.character.setText(actor.character);
            Picasso.get().load(ImdbIntegration.BASE_POSTER_URL+actor.profile_path).into(profile);
            this.actor=actor;
        }
    }
}
