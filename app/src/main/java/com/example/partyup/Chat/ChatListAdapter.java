package com.example.partyup.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partyup.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ChatListAdapter extends FirebaseRecyclerAdapter<Chat,ChatListAdapter.ChatsViewHolder>{

    public ChatListAdapter(
            @NonNull FirebaseRecyclerOptions<Chat> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatsViewHolder holder, int position, @NonNull Chat model)
    {
        holder.message.setText(model.getMessage());
        holder.time.setText(model.getMessageDate() + " " + model.getMessageTime());
        holder.sentBy.setText(model.getSentBy());
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_textview, parent, false);
        return new ChatListAdapter.ChatsViewHolder(view);
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder {
        TextView message, time, sentBy;
        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            message = itemView.findViewById(R.id.ChatMessage);
            time = itemView.findViewById(R.id.ChatTime);
            sentBy = itemView.findViewById(R.id.ChatTitle);
        }
    }

}