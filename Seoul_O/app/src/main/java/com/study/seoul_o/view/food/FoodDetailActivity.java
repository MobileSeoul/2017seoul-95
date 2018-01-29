package com.study.seoul_o.view.food;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.study.seoul_o.R;
import com.study.seoul_o.net.RestService;
import com.study.seoul_o.view.food.review.ReviewAdapter;
import com.study.seoul_o.view.food.review.ReviewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.study.seoul_o.data.Data.url;

public class FoodDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    public final String TITLE = "title";

    String title, kind, number, address, time, lat,lon, avg;
    String id;

    TextView food_time, food_address, food_detail_kind, food_call, food_detail_title, food_avg;
    ImageView  food_detail_img,food_detail_img1,food_detail_img2,food_detail_img3,food_detail_img4,food_detail_img5;

    //google map 변수
    Double location1 = 37.545983;
    Double location2 = 126.978120;
    GoogleMap mMap;


    private boolean isShow = false;
    private int scrollRange = -1;

    private String img,img1,img2,img3,img4,img5;

    private RecyclerView recyclerView;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        findview();

        init(); //서버 통신

        appBarInit();

        //Google Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.food_map);
        mapFragment.getMapAsync(this);

    }



    //이미지 클릭 리스너
    public void imgbtn(View v){ // 맛집사진들 버튼 이벤트
        ImageView f_img = (ImageView)findViewById(R.id.food_detail_img);
        Resources saveimg;

        switch (v.getId()) {

            case R.id.food_detail_img1:
                Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                        .load(url + img1) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                        .into((ImageView) findViewById(R.id.food_detail_img));
                break;

            case R.id.food_detail_img2:
                Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                        .load(url + img2) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                        .into((ImageView) findViewById(R.id.food_detail_img));
                break;

            case R.id.food_detail_img3:
                Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                        .load(url + img3) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                        .into((ImageView) findViewById(R.id.food_detail_img));
                break;

            case R.id.food_detail_img4:
                Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                        .load(url + img4) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                        .into((ImageView) findViewById(R.id.food_detail_img));
                break;

            case R.id.food_detail_img5:
                Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                        .load(url + img5) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                        .into((ImageView) findViewById(R.id.food_detail_img));
                break;
        }
    }





    @Override
    public void onMapReady(GoogleMap googleMap) { //GoogleMap Point

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng ponint = new LatLng(location1, location2);
        mMap.addMarker(new MarkerOptions().position(ponint).title("창수린"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ponint));
        mMap.setMinZoomPreference(18);

    }



    private void appBarInit(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.food_detail_toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#FFFFFF"));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.food_detail_app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle("창수린");
                    isShow = true;
                }else{
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.food_detail_toolbar);
        setSupportActionBar(toolbar);
    }


    // 타이틀 가져와서 서버 통신하는 메소드

    private void init() {
        Intent intent = getIntent();
        title = intent.getStringExtra(TITLE);

        Map<String, String> body = new HashMap<String, String>();
        body.put("title", title);
        RestService restService = new RestService.Creator().newRestService();
        final Call<ArrayList<Map<String,String>>> call = restService.getFoodDetail(body);
        call.enqueue(new Callback<ArrayList<Map<String,String>>>() {
            @Override
            public void onResponse(Call<ArrayList<Map<String,String>>> call, Response<ArrayList<Map<String,String>>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sorry, failed..", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) response.body();
                    ArrayList data = new ArrayList();
                    Log.d("###food", response.body().toString());


                    Map<String, String> foodData = list.get(0);

                    try {
                        id = foodData.get("id");
                    }catch (Exception e){
                        Log.d("#####",e.getMessage());
                    }
                    img = foodData.get("img");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_mainimg));

                    img1 = foodData.get("img1");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img1) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_img1));


                    img2 = foodData.get("img2");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img2) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_img2));

                    img3 = foodData.get("img3");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img3) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_img3));

                    img4 = foodData.get("img4");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img4) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_img4));

                    img5 = foodData.get("img5");
                    Glide.with(FoodDetailActivity.this) //getApp~~Context 을 바꿔야 된다..
                            .load(url + img5) //url + "mage03.jpeg" 이라고 넣으면 이미지를 가져옴
                            .into((ImageView) findViewById(R.id.food_detail_img5));

                    title = foodData.get("title");
                    food_detail_title.setText(title);

                    kind = foodData.get("kind");
                    food_detail_kind.setText(kind);

                    number = foodData.get("number");
                    food_call.setText(number);

                    address = foodData.get("address");
                    food_address.setText(address);

                    time = foodData.get("time");
                    food_time.setText(time);

                    lat = foodData.get("lat");

                    lon = foodData.get("lon");
                    LatLng ponint = new LatLng(location1, location2);
                    mMap.addMarker(new MarkerOptions().position(ponint).title(title));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(ponint));
                    mMap.setMinZoomPreference(18);


                    avg = foodData.get("avg");
                    food_avg=(TextView)findViewById(R.id.food_avg);
                    food_avg.setText("("+avg+")");

                    ratingBar = (RatingBar)findViewById(R.id.food_ratingbar);
                    ratingBar.setRating(Float.parseFloat(avg));

                    initRecyclerView();
                }catch (Exception e){
                    Log.d("######",e.getMessage());
                }
            }


            @Override
            public void onFailure(Call<ArrayList<Map<String,String>>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();


            }
        });
    }


    public void findview(){

        food_time=(TextView)findViewById(R.id.food_time) ;
        food_time.setText("11:00~12:00");
        food_address=(TextView)findViewById(R.id.food_address) ;
        food_address.setText("인천광역시 서구 마전동 대주피오레 103동 902호");
        food_call=(TextView)findViewById(R.id.food_call) ;
        food_call.setText("032-568-0289");
        food_detail_kind = (TextView)findViewById(R.id.food_detail_kind);
        food_detail_kind.setText("한식");
        food_detail_title = (TextView)findViewById(R.id.food_detail_title);
        food_detail_title.setText("서울화반");

        String avg = "4.5";
        food_avg=(TextView)findViewById(R.id.food_avg);
        food_avg.setText("("+avg+")");
    }




    private void initRecyclerView(){

        final ArrayList<ReviewItem> data = new ArrayList<>();

        Map<Object, Object> body = new HashMap<Object, Object>();
        body.put("index",id);
        RestService restService = new RestService.Creator().newRestService();
        final Call<Object> call = restService.getFoodReview(body);
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
                        Map<String, String> foodData = list.get(i);

                        String id = foodData.get("user");
                        String content = foodData.get("content");
                        String star = foodData.get("star");

                        data.add(new ReviewItem(id, content, star));
                    }

                    LinearLayoutManager layoutManager = new LinearLayoutManager(FoodDetailActivity.this);
                    ReviewAdapter adapter = new ReviewAdapter(data);

                    recyclerView = (RecyclerView) findViewById(R.id.food_listview);
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



        Button reviewBtn = (Button) findViewById(R.id.review_button);
        reviewBtn.setText("전체보기 >");
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodDetailActivity.this, FoodReviewActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
    }


}



