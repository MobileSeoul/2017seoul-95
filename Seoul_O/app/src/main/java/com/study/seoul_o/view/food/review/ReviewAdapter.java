package com.study.seoul_o.view.food.review;

import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.study.seoul_o.R;

import java.util.ArrayList;

/**
 * Created by yjk on 2017. 10. 28..
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ItemViewHolder>{
    ArrayList<ReviewItem> mItems;

    public ReviewAdapter(ArrayList<ReviewItem> items){
        mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent,false);

        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.id_textview.setText(mItems.get(position).getId());
        holder.content_textview.setText(mItems.get(position).getContent());
        holder.ratingBar.setRating(mItems.get(position).getStar());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView id_textview;
        private TextView content_textview;
        private RatingBar ratingBar;

        public ItemViewHolder(View itemView) {
            super(itemView);
            id_textview = (TextView) itemView.findViewById(R.id.review_id);
            content_textview = (TextView) itemView.findViewById(R.id.review_content);
            ratingBar = (RatingBar) itemView.findViewById(R.id.review_ratingbar);
        }
    }

}
