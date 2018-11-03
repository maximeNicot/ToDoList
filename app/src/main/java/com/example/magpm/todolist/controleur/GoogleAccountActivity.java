package com.example.magpm.todolist.controleur;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.magpm.todolist.R;
import com.google.firebase.auth.FirebaseAuth;

public class GoogleAccountActivity extends AppCompatActivity {

    private Button mLougoutBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_account);

        mAuth = FirebaseAuth.getInstance();
        //Truc qui est fait apres le signOut
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(GoogleAccountActivity.this, GoogleSignInActivity.class));
                }
            }
        };

        mLougoutBtn = (Button) findViewById(R.id.logoutGoogle);
        mLougoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
