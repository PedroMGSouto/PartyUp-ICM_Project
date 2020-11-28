package com.example.partyup.Chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.partyup.Login.Frag_Login;
import com.example.partyup.R;
import com.example.partyup.Requests.Frag_NewRequest;
import com.example.partyup.Requests.Request;
import com.example.partyup.Requests.RequestListAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;

public class Frag_Chat extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;
    HashMap<String,String> chatData;
    private RecyclerView recyclerView;
    ChatListAdapter adapter;
    HashMap<Object,Object> chatID;
    EditText message;
    Button send;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
    Date date;

    public Frag_Chat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag__chat, container, false);

        message = v.findViewById(R.id.messageEditText);
        send = v.findViewById(R.id.sendButton);

        recyclerView = v.findViewById(R.id.messageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String userID = Frag_Login.mAuth.getCurrentUser().getEmail();
        String hash = new String(Hex.encodeHex(DigestUtils.md5(userID)));

        ref = database.getReference("activeChats/"+hash);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue()==null){
                    Toast.makeText(getContext(),"Please join a party!",Toast.LENGTH_SHORT);
                    return;
                }
                chatID = (HashMap<Object, Object>) snapshot.getValue();
                String id =(String) chatID.get("chat");

                ref2 = database.getReference("chatMessages/"+id);
                Query query2 =ref2.orderByChild("time");
                FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                        .setQuery(query2, Chat.class)
                        .build();

                adapter = new ChatListAdapter(options);
                adapter.startListening();
                recyclerView.setAdapter(adapter);


                send.setOnClickListener(v1 -> {

                    System.out.println("Clicked!");

                    date = new Date();
                    chatData = new HashMap<>();
                    chatData.put("message",message.getText().toString());
                    chatData.put("messageDate",dateFormat.format(date));
                    chatData.put("messageTime",timeFormat.format(date));
                    chatData.put("time",dateFormat.format(date) + " " +timeFormat2.format(date));
                    chatData.put("sentBy",Frag_Login.mAuth.getCurrentUser().getDisplayName());


                    String requestUID = Frag_Login.mAuth.getCurrentUser().getEmail()+timeFormat2.format(date);
                    String hash2 = new String(Hex.encodeHex(DigestUtils.md5(requestUID)));


                    ref3 = database.getReference("chatMessages/"+id+"/"+hash2);

                    ref3.setValue(chatData);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}