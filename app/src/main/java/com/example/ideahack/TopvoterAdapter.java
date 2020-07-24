package com.example.ideahack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopvoterAdapter extends BaseAdapter {


    Context context;

    List<UserModel> listModelList;

    public TopvoterAdapter(Context context, List<UserModel> listModelList) {
        this.context = context;
        this.listModelList = listModelList;
    }

    @Override
    public int getCount() {
        return listModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.view_tem_layout,null,false);


        ImageView imageView;
        TextView title, subtitle,point;


        title = view.findViewById(R.id.itemmain_title);
        subtitle = view.findViewById(R.id.itemmain_subtitle);
        imageView = view.findViewById(R.id.itemmain_img);
        point = view.findViewById(R.id.pointId);

        title.setText(listModelList.get(i).getUsername());
        subtitle.setText(listModelList.get(i).getUniversity_id());
        point.setText(String.valueOf(listModelList.get(i).getGiveVote()));

        imageView.setImageResource(R.drawable.proico);


        return view;
    }
}

