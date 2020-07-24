package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText emailid,password;
    Button signIn;

    FirebaseAuth mAuth;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        emailid=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signIn=findViewById(R.id.signinbtn);

        signIn.setOnClickListener(this);

        progressBar=findViewById(R.id.progressbarLogin);




    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,AdminPanel.class));

        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signinbtn) {
            String email = emailid.getText().toString().trim();
            String pass = password.getText().toString();

            if (email.isEmpty()) {
                emailid.setError("Email is required");
                emailid.requestFocus();
                return;


            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailid.setError("invalid email");
                emailid.requestFocus();
                return;

            }
            if (pass.isEmpty()) {
                password.setError("Password required");
                password.requestFocus();
                return;
            }
            if (pass.length() < 6) {
                password.setError("Password length must be minimum of 6");
                password.requestFocus();
                return;

            }
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful())
                    {
                        startActivity(new Intent(MainActivity.this,AdminPanel.class));
                        finish();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }
            });


        }
    }
}
