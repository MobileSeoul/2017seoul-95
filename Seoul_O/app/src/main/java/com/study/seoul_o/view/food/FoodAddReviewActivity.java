package com.study.seoul_o.view.food;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.study.seoul_o.R;
import com.study.seoul_o.net.RestService;
import com.study.seoul_o.view.food.review.ReviewAdapter2;
import com.study.seoul_o.view.food.review.ReviewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodAddReviewActivity extends Activity {
    private String id;

    private EditText idEditText, contentEditText;
    private RatingBar starRatingBar;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        getWindow().setAttributes(layoutParams);


        setContentView(R.layout.activity_food_add_review);

        init();
    }



    private void init(){
        id = getIntent().getStringExtra("id");

        idEditText = (EditText) findViewById(R.id.review_add_id);
        contentEditText = (EditText) findViewById(R.id.review_add_content);
        starRatingBar = (RatingBar) findViewById(R.id.review_add_star);
        addBtn = (Button) findViewById(R.id.review_add_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){

        Map<Object, Object> body = new HashMap<Object, Object>();
        body.put("index",id);
        body.put("id",idEditText.getText().toString());
        body.put("content",contentEditText.getText().toString());
        body.put("star",String.valueOf(starRatingBar.getRating()));
        RestService restService = new RestService.Creator().newRestService();
        final Call<Object> call = restService.addFoodReview(body);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sorry, failed..", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) response.body();
                    Log.d("###food add review", response.body().toString());

                    Map<String, String> foodData = list.get(0);

                    Toast.makeText(FoodAddReviewActivity.this, "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Log.d("######",e.getMessage());
                }
            }


            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
