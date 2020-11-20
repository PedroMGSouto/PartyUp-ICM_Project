package com.example.partyup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class RequestListAdapter extends FirebaseRecyclerAdapter<Request,RequestListAdapter.RequestsViewHolder>{

    public RequestListAdapter(
            @NonNull FirebaseRecyclerOptions<Request> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RequestsViewHolder holder, int position, @NonNull Request model)
    {
        holder.message.setText(model.getMessage());
        holder.game.setText(model.getGame());
        holder.partySize.setText(model.getPartySize());
        holder.messageDate.setText(model.getMessageDate());
        holder.messageTime.setText(model.getMessageTime());
        holder.sentBy.setText(model.getSentBy());
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_textview, parent, false);
        return new RequestListAdapter.RequestsViewHolder(view);
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {
        TextView message, game, partySize, messageDate, messageTime, sentBy;
        public RequestsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            game = itemView.findViewById(R.id.game);
            partySize = itemView.findViewById(R.id.partySize);
            messageDate = itemView.findViewById(R.id.messageDate);
            messageTime = itemView.findViewById(R.id.messageTime);
            sentBy = itemView.findViewById(R.id.sentBy);
        }
    }

}