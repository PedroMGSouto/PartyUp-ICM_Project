package com.example.partyup.Chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.partyup.Login.Frag_Login;
import com.example.partyup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class Frag_Friends extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    private RecyclerView recyclerView;
    private ArrayList<String> activeChats;

    public Frag_Friends() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag__friends_and_chat, container, false);
        String clientUID = Frag_Login.mAuth.getCurrentUser().getEmail();
        String hash = new String(Hex.encodeHex(DigestUtils.md5(clientUID)));
        ref = database.getReference("userChats/"+hash);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                activeChats = new ArrayList<>();
                for(DataSnapshot snap : snapshot.getChildren()){
                    activeChats.add(snap.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}