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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.partyup.R;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;


public class Frag_Register extends Fragment {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //FireBase DB
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    HashMap<String,String> hm = new HashMap<>(); //User Data

    //EmailCheck
    private final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);

    public Frag_Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag__register, container, false);

        //Get Register Button
        CardView doRegister = (CardView) v.findViewById(R.id.doRegisterButton);

        //Get Fields
        EditText name = (EditText)v.findViewById(R.id.nameField);
        EditText username = (EditText)v.findViewById(R.id.usernameField);
        EditText email = (EditText)v.findViewById(R.id.emailField);
        EditText password = (EditText)v.findViewById(R.id.passwordField);
        EditText rpassword = (EditText)v.findViewById(R.id.rpasswordField);
        RadioGroup sex = (RadioGroup) v.findViewById(R.id.sexField);


        //Register Button Click
        doRegister.setOnClickListener(v1 -> {

            //Gets all fields information
            String nameField = name.getText().toString();
            String userField = username.getText().toString();
            String emailField = email.getText().toString();
            String passField = password.getText().toString();
            String rpassField = rpassword.getText().toString();
            String sexField;

            int sexID = sex.getCheckedRadioButtonId();
            RadioButton sexSel = (RadioButton) v.findViewById(sexID);

            //Is sex selected?
            try {
                sexField = sexSel.getText().toString();
            }
            catch(Exception ex){
                sexField = "";
            }

            //Validate All Data Before Register
            if(validate(nameField,userField,emailField,passField,rpassField,sexField)){
                //Get DB reference with email
                String hash = new String(Hex.encodeHex(DigestUtils.md5(emailField)));
                //String hash = DigestUtils.md5Hex(emailField).toUpperCase(); STOPPED WORKING FOR NO REASON
                myRef = database.getReference("Users/"+hash);

                //Save data in user data hashmap
                hm.put("Email",emailField);
                hm.put("Name",nameField);
                hm.put("User",userField);
                hm.put("Sex",sexField);

                //Save to DB
                try {
                    mAuth.createUserWithEmailAndPassword(emailField, passField);
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userField).build();
                    mAuth.getCurrentUser().updateProfile(profileUpdates);
                    myRef.setValue(hm);
                    Frag_Login.mAuth=mAuth;
                }

                catch (Exception ex){
                    Toast.makeText(getActivity(),"Ups! Something went wrong...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Navigation.findNavController(v1).navigate(R.id.action_frag_Register_to_secondaryActivity);
            }
        });

        return v;
    }


    //Validate all register data
    private boolean validate(String name, String username, String email, String password, String rpassword, String sex){

        if(name.isEmpty()||username.isEmpty()||email.isEmpty()||password.isEmpty()||rpassword.isEmpty()||sex.isEmpty()){
            Toast.makeText(getActivity(),"Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!isAlpha(name)){
            Toast.makeText(getActivity(),"Invalid characters in name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(username.length()>20){
            Toast.makeText(getActivity(),"Username has a max of 20 chars", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!pat.matcher(email).matches()){
            Toast.makeText(getActivity(),"Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!password.equals(rpassword)){
            Toast.makeText(getActivity(),"Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length()<6){
            Toast.makeText(getActivity(),"Password's too small <6 chars", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isAlpha(String name) {
        name = name.trim().replaceAll("\\s+","");
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}