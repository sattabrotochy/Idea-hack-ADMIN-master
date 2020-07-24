package com.example.ideahack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private static final String TAG = "PostAdapter";

    Context context;
    List<PostModel>postModelList;



    public PostAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ideas_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final PostModel postModel = postModelList.get(position);




        FirebaseFirestore.getInstance().collection("userinfo").document(postModel.getUserID())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()){

                    DocumentSnapshot document = task.getResult();
                    String value = document.getString("username");
                    holder.username.setText(value);
                }

            }
        });


        holder.status.setText(postModel.getStatus());

        holder.createat.setText(TimeAgo.from(postModel.getCreate_at()));


        if (postModel.getImgaeurl().equals("No Image")){

            holder.image.setImageResource(R.drawable.images);
        }
        else {

            Picasso.get().load(postModel.getImgaeurl()).into(holder.image);
        }
        holder.title.setText(postModel.getTitle());
        holder.des.setText(postModel.getDescription());


        String urrr = String.valueOf(postModel.getUpvote());
        String drrr = String.valueOf(postModel.getDownvote());

        holder.upcount.setText(urrr);
        holder.downcount.setText(drrr);




        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,EditStatusActivity.class);

                intent.putExtra("id",postModel.getId());
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }





    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onItemClick(View view, int position, PostModel postModel);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public  class  MyViewHolder  extends RecyclerView.ViewHolder {



        ImageView propic,bookmark;
        TextView username,status,createat;
        ImageView image;
        TextView title,des,upvote,downvote,comment,upcount,downcount,commentcount;

        Button Edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            propic = itemView.findViewById(R.id.proicoID);
            username = itemView.findViewById(R.id.username_item);
            status = itemView.findViewById(R.id.StatusID);
            createat = itemView.findViewById(R.id.careate_atId);
            image = itemView.findViewById(R.id.item_icon);
            title = itemView.findViewById(R.id.item_title);
            des = itemView.findViewById(R.id.item_desId);
            upcount = itemView.findViewById(R.id.item_upvotecount);
            downcount = itemView.findViewById(R.id.item_downvotecount);
            commentcount = itemView.findViewById(R.id.item_comments);
            upvote = itemView.findViewById(R.id.upvoteID);
            downvote = itemView.findViewById(R.id.downvoteId);
            comment = itemView.findViewById(R.id.commentId);
            Edit = itemView.findViewById(R.id.EditId);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(view, position, postModelList.get(getAdapterPosition()));
                        }
                    }

                }
            });


        }
    }



    public void setfilter(List<PostModel> itemData) {
        postModelList = new ArrayList<>();
        postModelList.addAll(itemData);
        notifyDataSetChanged();

    }





}
