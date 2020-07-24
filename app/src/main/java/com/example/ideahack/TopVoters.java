package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopVoters extends AppCompatActivity {



    ListView listView;

    List<UserModel> listModelList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_voters);

        listView = findViewById(R.id.mainlistId2);

        Query query = FirebaseFirestore.getInstance().collection("userinfo").orderBy("giveVote", Query.Direction.DESCENDING).limit(10);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot doc : task.getResult()){

                        UserModel userModel = doc.toObject(UserModel.class);

                        listModelList.add(userModel);

                    }

                    TopvoterAdapter adapter = new TopvoterAdapter(TopVoters.this,listModelList);
                    listView.setAdapter(adapter);

                }

            }
        });




    }
}
