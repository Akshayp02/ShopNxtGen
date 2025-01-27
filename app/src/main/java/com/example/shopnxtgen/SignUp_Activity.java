package com.example.mrreco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity2 extends AppCompatActivity {
    AppCompatEditText loginEmail, loginPass;
    AppCompatButton Btnlogin, BtnLoginwithegoogle;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // Check if the user is already authenticated
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is already authenticated, redirect to the main activity
            startActivity(new Intent(SignIn_Activity.this, MainActivity.class));
            finish();
            return;
        }
        // Find IDs
        loginEmail = findViewById(R.id.loginemail);
        loginPass = findViewById(R.id.loginpass);
        Btnlogin = findViewById(R.id.btnlogin);
        BtnLoginwithegoogle = findViewById(R.id.btnloginwithgoogle);
        auth = FirebaseAuth.getInstance();

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for login button
                String email = loginEmail.getText().toString();
                String pass = loginPass.getText().toString();

                if (isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && isEmpty(pass)) {
                    // Code for login and other stuff
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(SignIn_Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn_Activity.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignIn_Activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(SignIn_Activity.this, "Please check your details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BtnLoginwithegoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for clicking on login with Google
                startActivity(new Intent(SignIn_Activity.this, SignUpActivity.class));
            }
        });
    }

    // Function
    public boolean isEmpty(String arg) {
        if (arg.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
