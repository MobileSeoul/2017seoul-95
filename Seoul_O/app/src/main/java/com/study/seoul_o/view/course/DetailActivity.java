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

public class DetailActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_course_detail01);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("남산회현 코스");
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
                markerB.title("남대문교회");
                markerB.position(new LatLng(37.555726, 126.974868));
                markerB.flat(true);
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerB);

                MarkerOptions markerC=new MarkerOptions();
                markerC.title("백범광장");
                markerC.position(new LatLng(37.555088, 126.979258));
                markerC.flat(true);
                markerC.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerC);

                MarkerOptions markerD=new MarkerOptions();
                markerD.title("숭례문");
                markerD.position(new LatLng(37.560174, 126.975259));
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
                        intent.putExtra("courseTitle","남산회현 코스");
                        intent.putExtra("road1","문화역서울 284");
                        intent.putExtra("road2","남대문 교회");
                        intent.putExtra("road3","백범광장");
                        intent.putExtra("road4","숭례문");

                        intent.putExtra("road1x",37.556121);
                        intent.putExtra("road1y",126.971636);

                        intent.putExtra("road2x",37.555726);
                        intent.putExtra("road2y",126.974868);

                        intent.putExtra("road3x",37.555088);
                        intent.putExtra("road3y",126.979258);

                        intent.putExtra("road4x",37.560174);
                        intent.putExtra("road4y",126.975259);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                    }
                });
            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recycler);

        members.add(new ListViewMember("step.01","문화역서울 284",R.drawable.img_course_01_01,"최초의 서울역은 1900년 염천교 부근에 10평짜리 목재 건물로 지어졌습니다. 지금의 위치에 서울역(경성역)이 지어진 것은 1925년 입니다. 19세기 절충주의 양식으로 지어진 크고 화려한 서울역사는 동양에서는 동경역에 이어 두 번째로 큰 규모였습니다. 2004년에 새로운 역사가 지어지면서 현재 구 역사는 복합문화공간인 ‘문화역서울284’로 사용되고 있습니다."));
        members.add(new ListViewMember("step.02","남대문 교회",R.drawable.img_course_01_02,"남대문교회의 역사는 우리나라 최초의 서양식 병원인 제중원의 부속 교회 ‘제중원교회’로 시작되었습니다. 교회 건물은 1969년에 완공된 고딕양식의 석조 건물로 한국의 근대 건축가인 박동진이 설계를 맡았습니다. 남대문교회를 다 둘러보고 난 뒤에 서울스퀘어와 남대문교회 사이에 위치하고 있는 공원에서 잠시 쉬어가는 것을 추천합니다. "));
        members.add(new ListViewMember("step.03","백범광장",R.drawable.img_course_01_03,"백범광장은 사람들에게 잘 알려지지 않아 인적이 드문 평화로운 남산 속 공원입니다. 지금의 여유로운 분위기와는 반대로 이곳의 얼굴은 도시개발 과정 속에서 수차례 변해왔습니다. 해방 이후 이승만 대통령은 ‘국회의사당’을 이곳에 설립하려 했지만, 대통령 하야와 연이은 5.16군사정변으로 인해 무산되었습니다. 2009년 ‘남산르네상스’라는 이름으로 성벽이 복원되어 지금의 모습을 갖추게 되었습니다."));
        members.add(new ListViewMember("step.04","숭례문",R.drawable.img_course_01_04,"4대문과 4소문으로 이루어진 성곽도시 한양의 가장 오래된 문인 숭례문은 1398년 태조 이성계가 한양을 도읍지로 정하면서 축조되었습니다. 밖으로는 칠패시장이라는 난전이, 안으로는 남대문시장의 전신이 형성되어 있어 4대문 중에서도 가장 사람들의 출입이 많았던 문입니다. 그리고 2008년 화재로 2층 문루와 1층 문루의 일부가 소실되었고, 현재는 복원된 모습입니다."));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter= new MyAdapter(members,this);
        recyclerView.setAdapter(adapter);
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