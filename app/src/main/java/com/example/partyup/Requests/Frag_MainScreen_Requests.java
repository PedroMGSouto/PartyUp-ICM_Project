package com.example.partyup.Requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


        return view;
    }
}