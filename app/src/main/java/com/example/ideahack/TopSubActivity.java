package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TopSubActivity extends AppCompatActivity {


    ListView listView;

    List<UserModel> listModelList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sub);


        listView = findViewById(R.id.mainlistId);

        Query query = FirebaseFirestore.getInstance().collection("userinfo").orderBy("submit", Query.Direction.DESCENDING).limit(10);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot doc : task.getResult()){

                        UserModel userModel = doc.toObject(UserModel.class);

                        listModelList.add(userModel);

                    }

                    TopSubmiterAdapter adapter = new TopSubmiterAdapter(TopSubActivity.this,listModelList);
                    listView.setAdapter(adapter);

                }

            }
        });




    }
}
