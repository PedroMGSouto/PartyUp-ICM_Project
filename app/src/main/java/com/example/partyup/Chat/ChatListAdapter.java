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
        holder.lastMessageSent.setText(model.getLastMessageSent());
        holder.more_properties.setText(model.getMore_properties());
        //holder.members.setText(model.getMembers());
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_textview, parent, false);
        return new ChatListAdapter.ChatsViewHolder(view);
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder {
        TextView lastMessageSent, members, more_properties;
        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            lastMessageSent = itemView.findViewById(R.id.ChatLastMessage);
            //members = itemView.findViewById(R.id.partySize);
            more_properties = itemView.findViewById(R.id.ChatTitle);;
        }
    }

}