package com.example.partyup.Requests;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.partyup.Login.Frag_Login;
import com.example.partyup.Login.InitialGames.Frag_InitialGameSelection;
import com.example.partyup.R;
import com.example.partyup.Scroll.ScreenSlidePageFragment;
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
import java.util.HashMap;
import java.util.TreeSet;

public class Frag_NewRequest extends Fragment {
    public TreeSet<String> gameList = new TreeSet<>();
    private AutoCompleteTextView actv;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference ref3;
    DatabaseReference ref4;
    private RecyclerView recyclerView;
    private CardView doRequest;
    private EditText partyn;
    private HashMap<String,String> requestData;
    private HashMap<String,String> chatData;
    private HashMap<String,String> activeChat;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
    Date date;

    public Frag_NewRequest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag__new_request, container, false);

        doRequest= (CardView) v.findViewById(R.id.doNewRequest);
        partyn = v.findViewById(R.id.editTextNumber);

        ref = database.getReference("Games");
        actv = v.findViewById(R.id.autoCompleteTextView);

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot game : snapshot.getChildren()){
                    gameList.add(game.getValue().toString());
                }
                String[] gameListA = gameList.toArray(new String[gameList.size()]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item,gameListA);
                actv.setThreshold(1);
                actv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        doRequest.setOnClickListener(v1 -> {
            String gameText = actv.getText().toString();
            String partyText = partyn.getText().toString();

            if(gameText.isEmpty()|| partyText.isEmpty()){
                Toast.makeText(getActivity(),"Please fill all fields!",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!gameList.contains(gameText)){
                Toast.makeText(getActivity(),"Invalid game!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(Integer.parseInt(partyText)<=1){
                Toast.makeText(getActivity(),"Min party size is 2!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(Integer.parseInt(partyText)>10){
                Toast.makeText(getActivity(),"Max party size is 10!",Toast.LENGTH_SHORT).show();
                return;
            }


            date = new Date();

            requestData=new HashMap<>();
            requestData.put("game",gameText);
            requestData.put("messageDate",dateFormat.format(date));
            requestData.put("messageTime", timeFormat.format(date));
            requestData.put("time",dateFormat.format(date) + " " +timeFormat2.format(date));
            requestData.put("partyCurr","1");
            requestData.put("partySize",partyText);
            requestData.put("sentBy", Frag_Login.mAuth.getCurrentUser().getDisplayName());


            chatData = new HashMap<>();
            chatData.put("message","Link Start! ChatRoom initialized!");
            chatData.put("messageDate",dateFormat.format(date));
            chatData.put("messageTime",timeFormat.format(date));
            chatData.put("time",dateFormat.format(date) + " " +timeFormat.format(date));
            chatData.put("sentBy","PartyUp");

            String requestUID = Frag_Login.mAuth.getCurrentUser().getEmail()+timeFormat2.format(date);
            String firstMessageId = "Admin"+timeFormat2.format(date);
            String hash = new String(Hex.encodeHex(DigestUtils.md5(requestUID)));
            String hash2 = new String(Hex.encodeHex(DigestUtils.md5(firstMessageId)));
            String hash3 = new String(Hex.encodeHex(DigestUtils.md5(Frag_Login.mAuth.getCurrentUser().getEmail())));
            ref2 = database.getReference("Requests/"+hash);
            ref3 = database.getReference("chatMessages/"+hash+"/"+hash2);
            ref4 = database.getReference("activeChats/"+hash3);

            activeChat = new HashMap<>();
            activeChat.put("chat",hash);
            requestData.put("id",hash);

            try {
                ref2.setValue(requestData);
                ref3.setValue(chatData);
                ref4.setValue(activeChat);
            }

            catch (Exception ex){
                Toast.makeText(getActivity(),"Ups! Something went wrong...", Toast.LENGTH_SHORT).show();
                return;
            }


            Navigation.findNavController(v).navigate(R.id.action_Home_to_frag_Chat);
            actv.setText("");
            partyn.setText("");

        });
        return v;
    }
}