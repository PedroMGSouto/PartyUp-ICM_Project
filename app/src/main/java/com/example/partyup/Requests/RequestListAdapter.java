package com.example.partyup.Requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partyup.Login.Frag_Login;
import com.example.partyup.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;


public class RequestListAdapter extends FirebaseRecyclerAdapter<Request,RequestListAdapter.RequestsViewHolder>{

    public RequestListAdapter(
            @NonNull FirebaseRecyclerOptions<Request> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RequestsViewHolder holder, int position, @NonNull Request model)
    {
        holder.game.setText(model.getGame());
        holder.partySize.setText(model.getPartyCurr()+"/"+model.getPartySize());
        holder.messageDate.setText(model.getMessageDate());
        holder.messageTime.setText(model.getMessageTime());
        holder.sentBy.setText(model.getSentBy());
        holder.id.setText(model.getId());


        holder.joinParty.setOnClickListener(new View.OnClickListener() {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            @Override
            public void onClick(View v) {

                HashMap<String,String> activeChat = new HashMap<>();
                DatabaseReference ref4;

                String hash3 = new String(Hex.encodeHex(DigestUtils.md5(Frag_Login.mAuth.getCurrentUser().getEmail())));
                ref4 = database.getReference("activeChats/"+hash3);

                activeChat.put("chat",model.getId());
                ref4.setValue(activeChat);

                Navigation.findNavController(v).navigate(R.id.action_Home_to_frag_Chat);

            }
        });
    }

    @NonNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_textview, parent, false);
        return new RequestListAdapter.RequestsViewHolder(view);
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {
        TextView game, partySize, messageDate, messageTime, sentBy, id;
        Button joinParty;
        public RequestsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            joinParty = itemView.findViewById(R.id.joinPartyButton);

            game = itemView.findViewById(R.id.game);
            partySize = itemView.findViewById(R.id.partySize);
            messageDate = itemView.findViewById(R.id.messageDate);
            messageTime = itemView.findViewById(R.id.messageTime);
            sentBy = itemView.findViewById(R.id.sentBy);
            id = itemView.findViewById(R.id.reqID);
        }
    }

}