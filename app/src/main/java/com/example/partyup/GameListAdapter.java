package com.example.partyup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.TreeSet;

public class GameListAdapter extends RecyclerView.Adapter<GameViewHolder>{
    public static TreeSet<String> gameList;
    private LayoutInflater gInflater;

    public GameListAdapter(Context context, TreeSet<String> gameList) {
        gInflater = LayoutInflater.from(context);
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View gItemView = gInflater.inflate(R.layout.game_textview, parent, false);

        return new GameViewHolder(gItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        String mCurrent = gameList.toArray()[position].toString();
        holder.view.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}