package com.example.partyup.Requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partyup.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Frag_MainScreen_Requests extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private RecyclerView recyclerView;
    RequestListAdapter adapter;



    public Frag_MainScreen_Requests() {
        // Required empty public constructor
    }

    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot game : snapshot.getChildren()){
                    for (DataSnapshot id : game.getChildren()){
                        if(id != null){
                            Request request = id.getValue(Request.class);
                            if(request!=null){
                                String message = request.getMessage();
                                String messageDate = request.getMessageDate();
                                String messageTime = request.messageTime;
                                String sentBy = request.sentBy;
                                HashMap<String,String> hm = new HashMap<>();
                                hm.put("message",message);
                                hm.put("messageDate",messageDate);
                                hm.put("messageTime",messageTime);
                                hm.put("sentBy",sentBy);
                                requests.add(hm);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Failed to retrieve data",Toast.LENGTH_SHORT);
            }
        });**/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag__main_screen__requests, container, false);

        ref = database.getReference("Requests");
        recyclerView = view.findViewById(R.id.recyclerRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query =ref.orderByChild("time");
        FirebaseRecyclerOptions<Request> options = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(query, Request.class)
                .build();

        adapter = new RequestListAdapter(options);
        recyclerView.setAdapter(adapter);

        /*recyclerView = view.findViewById(R.id.recyclerRequests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RequestListAdapter(getContext(),requests));*/

        return view;
    }
}