package com.example.partyup.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.example.partyup.Login.FirebaseAuthWithGoogle;
import com.example.partyup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Frag_Login extends Fragment{
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public Frag_Login() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser); -> Replace by call to change to main screen
    }

    public void doLogin(String email,String password, View v){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new FirebaseAuthWithGoogle(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SuccessTag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Navigation.findNavController(getView()).navigate(R.id.action_frag_Login_to_secondaryActivity);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("FailTag", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(),"Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag__login, container, false);

        CardView dologin = (CardView)v.findViewById(R.id.doLoginButton);
        EditText emailtext = (EditText)v.findViewById(R.id.userText);
        EditText passtext = (EditText)v.findViewById(R.id.passText);


        dologin.setOnClickListener(v1 -> {
            String email = emailtext.getText().toString();
            String password = passtext.getText().toString();

            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(getActivity(),"Email or pass is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            doLogin(email,password,v1);

        });


        return v;
    }
}