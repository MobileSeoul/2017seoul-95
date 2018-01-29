package com.study.seoul_o.view.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.study.seoul_o.R;
import com.study.seoul_o.net.RestService;
import com.study.seoul_o.view.food.review.ReviewAdapter;
import com.study.seoul_o.view.food.review.ReviewAdapter2;
import com.study.seoul_o.view.food.review.ReviewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodReviewActivity extends Activity {
    private RecyclerView recyclerView;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_review);

        init();

        initRecyclerView();
    }



    private void init(){
        id = getIntent().getStringExtra("id");

        Button plusBtn = (Button) findViewById(R.id.review_plus_button);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodReviewActivity.this, FoodAddReviewActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        final ArrayList<ReviewItem> data = new ArrayList<>();

        Map<Object, Object> body = new HashMap<Object, Object>();
        body.put("index",id);
        RestService restService = new RestService.Creator().newRestService();
        final Call<Object> call = restService.getFoodReview2(body);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sorry, failed..", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) response.body();
                    Log.d("###foodreview", response.body().toString());

                    for(int i=0; i<list.size(); i++){
                        Log.d("######" , "i : " + i);
                        Map<String, String> foodData = list.get(i);

                        String id = foodData.get("user");
                        String content = foodData.get("content");
                        String star = foodData.get("star");

                        data.add(new ReviewItem(id, content, star));
                    }

                    LinearLayoutManager layoutManager = new LinearLayoutManager(FoodReviewActivity.this);
                    ReviewAdapter2 adapter = new ReviewAdapter2(data);

                    recyclerView = (RecyclerView) findViewById(R.id.review_list);
                    recyclerView.setLayoutManager(layoutManager);

                    recyclerView.setAdapter(adapter);


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
