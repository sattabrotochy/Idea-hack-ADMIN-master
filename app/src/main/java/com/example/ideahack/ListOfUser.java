package com.example.ideahack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ListOfUser extends AppCompatActivity {


    ListView listView;

    List<UserModel> listModelList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_user);


        listView = findViewById(R.id.mainlistId23);

        Query query = FirebaseFirestore.getInstance().collection("userinfo").orderBy("create_at", Query.Direction.DESCENDING);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    for (DocumentSnapshot doc : task.getResult()){

                        UserModel userModel = doc.toObject(UserModel.class);

                        listModelList.add(userModel);

                    }

                    TotalUserAdapter adapter = new TotalUserAdapter(ListOfUser.this,listModelList);
                    listView.setAdapter(adapter);

                }

            }
        });




    }
}

