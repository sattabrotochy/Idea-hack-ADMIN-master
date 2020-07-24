package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EditStatusActivity extends AppCompatActivity {



    Spinner spinner;

    Button button;

    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_status);


        spinner = findViewById(R.id.SpinerIDEdit);

        button = findViewById(R.id.spinerSaveId);


        Intent intent = getIntent();


      final   String id = intent.getStringExtra("id");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 s = spinner.getItemAtPosition(i).toString();



                Log.d(TAG, "onItemSelected: " +s);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore.getInstance().collection("Ideas").document(id).update("status",s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(EditStatusActivity.this, "Store.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditStatusActivity.this,AllPostActivity.class));
                            finish();
                        }

                    }
                });


            }
        });


    }
}
