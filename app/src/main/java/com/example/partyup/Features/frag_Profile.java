package com.example.partyup.Features;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.partyup.Login.Frag_Login;
import com.example.partyup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;

public class frag_Profile extends Fragment {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    TextView pname;
    TextView puser;
    TextView pmail;
    TextView psex;
    TextView pjoined;
    TextView pstarted;
    TextView qrscanned;

    public frag_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag__profile, container, false);

        pname = v.findViewById(R.id.Profile_Name);
        puser = v.findViewById(R.id.Profile_User);
        pmail = v.findViewById(R.id.Profile_Mail);
        psex = v.findViewById(R.id.Profile_Sex);
        pjoined = v.findViewById(R.id.Profile_PartiesJoined);
        pstarted = v.findViewById(R.id.Profile_PartiesStarted);
        qrscanned = v.findViewById(R.id.Profile_QRScanned);

        String userID = Frag_Login.mAuth.getCurrentUser().getEmail();
        System.out.println(userID);
        String hash = new String(Hex.encodeHex(DigestUtils.md5(userID)));

        ref = database.getReference("Users/"+hash);
        System.out.println(hash);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                puser.setText(snapshot.child("User").getValue().toString());
                pname.setText(snapshot.child("Name").getValue().toString());
                pmail.setText(snapshot.child("Email").getValue().toString());
                psex.setText(snapshot.child("Sex").getValue().toString());
                pjoined.setText(snapshot.child("PJoined").getValue().toString());
                pstarted.setText(snapshot.child("PStarted").getValue().toString());
                qrscanned.setText(snapshot.child("QRScanned").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}