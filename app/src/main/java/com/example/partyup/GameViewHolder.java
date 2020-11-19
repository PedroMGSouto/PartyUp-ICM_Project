package com.example.partyup;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView view;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.game);
        view.setOnClickListener(this);
    }

    public TextView getView(){
        return view;
    }

    @Override
    public void onClick(View v) {
        TextView game = (TextView) v;

        Context context = v.getContext();

        //Frag_InitialGameSelection.addSelectedGames(game.getText().toString());
        v.setSelected(true);

    }
}