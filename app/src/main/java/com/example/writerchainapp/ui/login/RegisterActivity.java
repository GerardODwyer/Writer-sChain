package com.example.writerchainapp.ui.login;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writerchainapp.Constructors.Users;
import com.example.writerchainapp.MainActivity;
import com.example.writerchainapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private EditText username;
    private EditText displayName;
    private EditText password;
    private Button registerButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("Users");
        username = findViewById(R.id.reg_email);
        displayName = findViewById(R.id.reg_name);
        password = findViewById(R.id.reg_password);
        registerButton = findViewById(R.id.reg_btn);

        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(username, password, displayName);
            }
        });

    }


    public void register(EditText username, EditText password, EditText displayName) {
        String email = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        final String disName = displayName.getText().toString().trim();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Could not sign you up", Toast.LENGTH_LONG).show();
                            } else {
                                Users user = new Users();
                                String uuid = auth.getUid();
                                user.setUserID(uuid);
                                user.setUserDisplayName(disName);
                                user.setUserChainCount(0);
                                user.setUserChapterCount(0);
                                user.setUserAdmin(false);
                                dbReference.child(uuid).setValue(user);
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
            // TODO: 12/04/2018  Add user to firebase
//            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        } else {
            Toast.makeText(RegisterActivity.this, "All fields must be filled in to register", Toast.LENGTH_LONG).show();
        }
    }
}

