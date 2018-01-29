package com.study.seoul_o.view.course;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.study.seoul_o.R;

import java.util.ArrayList;

public class DetailActivity4 extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;

    Toolbar toolbar;

    GoogleMap map;


    NestedScrollView nestedScrollView;

    RecyclerView recyclerView;
    MyAdapter adapter;

    ArrayList<ListViewMember> members=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail04);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("서울역통합 코스");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        final FragmentManager manager=getFragmentManager();
        MapFragment mapFragment=(MapFragment) manager.findFragmentById(R.id.map);
        nestedScrollView=(NestedScrollView)findViewById(R.id.nested);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map=googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.556121, 126.971636), 14));

                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(37.556121, 126.971636)).title("문화역서울 284"));
                marker.showInfoWindow();

                MarkerOptions markerA=new MarkerOptions();
                markerA.title("문화역서울 284");
                markerA.position(new LatLng(37.556121, 126.971636));
                markerA.flat(true);
                markerA.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerA);

                MarkerOptions markerB=new MarkerOptions();
                markerB.title("한양도성");
                markerB.position(new LatLng(37.559099, 126.975743));
                markerB.flat(true);
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerB);

                MarkerOptions markerC=new MarkerOptions();
                markerC.title("스퀘어가든");
                markerC.position(new LatLng(37.555512, 126.974430));
                markerC.flat(true);
                markerC.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerC);

                MarkerOptions markerD=new MarkerOptions();
                markerD.title("손기정체육공원");
                markerD.position(new LatLng(37.554307, 126.966449));
                markerD.flat(true);
                markerD.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerD);

                UiSettings settings=map.getUiSettings();
                settings.setScrollGesturesEnabled(false);
                settings.setZoomControlsEnabled(false);
                settings.setZoomGesturesEnabled(false);

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        Intent intent=new Intent(getApplicationContext(),MapDetailActivity.class);
                        intent.putExtra("courseTitle","서울역통합 코스");
                        intent.putExtra("road1","문화역서울 284");
                        intent.putExtra("road2","한양도성");
                        intent.putExtra("road3","스퀘어가든");
                        intent.putExtra("road4","손기정체육공원");

                        intent.putExtra("road1x",37.556121);
                        intent.putExtra("road1y",126.971636);

                        intent.putExtra("road2x",37.559099);
                        intent.putExtra("road2y",126.975743);

                        intent.putExtra("road3x",37.555512);
                        intent.putExtra("road3y",126.974430);

                        intent.putExtra("road4x",37.554307);
                        intent.putExtra("road4y",126.966449);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                    }
                });
            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recycler);

        members.add(new ListViewMember("step.01","문화역서울 284",R.drawable.img_course_04_01,"최초의 서울역은 1900년 염천교 부근에 10평짜리 목재 건물로 지어졌습니다. 지금의 위치에 서울역(경성역)이 지어진 것은 1925년 입니다. 19세기 절충주의 양식으로 지어진 크고 화려한 서울역사는 동양에서는 동경역에 이어 두 번째로 큰 규모였습니다. 2004년에 새로운 역사가 지어지면서 현재 구 역사는 복합문화공간인 ‘문화역서울284’로 사용되고 있습니다."));
        members.add(new ListViewMember("step.02","한양도성",R.drawable.img_course_04_02,"한양도성(사적 제 10호)은 서울의 주위를 둘러싸고 있는 조선시대 도성입니다. 서울역통합코스에서 볼 수 있는 한양도성은 소월로를 따라 남아 있는 일부 구간으로 가을에는 노란 은행잎도 감상할 수 있는 낭만적인 구간입니다."));
        members.add(new ListViewMember("step.03","스퀘어가든",R.drawable.img_course_04_03,"스퀘어 가든은 현재 서울역에 내리면 가장 먼저 볼 수 있는 서울스퀘어 빌딩과 그 뒤에 숨어있는 남대문교회 사이에 있는 작은 정원에 나름대로 이름을 붙인 곳입니다. 대우건설과 함께 승승장구하던 대우는 당시 대우빌딩 일대를 ‘대우왕국’으로 만들기 위해 토지를 매입합니다. 그 과정에서 남대문 교회 토지를 매입할 수 없어 매입 대신 사이의 벽을 허물고 함께 정원을 만들어 사용하게 되었답니다. "));
        members.add(new ListViewMember("step.04","손기정체육공원",R.drawable.img_course_04_04,"손기정체육공원은 손기정 선수의 모교인 양정보통학교가 있던 곳입니다. 학교 운동장이 있던 자리에는 1936년 베를린 올림픽에서 우승할 당시 지중해 지방에서만 자라는 월계수를 대신하여 수여받은 대왕참나무가 심겨져 있습니다. 공원 내부에는 1904년에 지어진 기존의 학교 건물을 보수하여 만든 주민체육시설, 문화시설, 기념관 등이 있습니다."));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter= new MyAdapter(members,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
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

    public void clickCircleA(View v){
        recyclerView.smoothScrollToPosition(4);
    }
    public void clickCircleB(View v){
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
                Log.i("aa","aa");
            }
        });
    }
    public void clickCircleC(View v){
        recyclerView.smoothScrollToPosition(2);
    }
    public void clickCircleD(View v){
        recyclerView.smoothScrollToPosition(3);
    }
}