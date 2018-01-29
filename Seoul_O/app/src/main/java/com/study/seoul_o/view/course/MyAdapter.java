package com.study.seoul_o.view.course;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.seoul_o.R;

import java.util.ArrayList;

/**
 * Created by spurs on 2017/09-11(011).
 */

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<ListViewMember> members;
    Context context;

    public MyAdapter(ArrayList<ListViewMember> members, Context context) {
        this.members = members;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.listview_item,parent,false);

        ViewHolder holder=new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).textStep.setText(members.get(position).step);
        ((ViewHolder)holder).textTitle.setText(members.get(position).title);
        Glide.with(context).load(members.get(position).imgId).into(((ViewHolder)holder).img);
        ((ViewHolder)holder).textText.setText(members.get(position).text);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textStep;
        TextView textTitle;
        ImageView img;
        TextView textText;

        public ViewHolder(View itemView) {
            super(itemView);
            textStep=(TextView)itemView.findViewById(R.id.tv1);
            textTitle=(TextView)itemView.findViewById(R.id.tv2);
            img=(ImageView)itemView.findViewById(R.id.img);
            textText=(TextView)itemView.findViewById(R.id.tv3);
        }
    }
}
