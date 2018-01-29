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

public class DetailActivity2 extends AppCompatActivity {

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
        setContentView(R.layout.activity_course_detail02);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("중림충정 코스");
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

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.559516, 126.970239), 14));

                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(37.559516, 126.970239)).title("염천교 수제화거리"));
                marker.showInfoWindow();

                MarkerOptions markerA=new MarkerOptions();
                markerA.title("염천교 수제화거리");
                markerA.position(new LatLng(37.559516, 126.970239));
                markerA.flat(true);
                markerA.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerA);

                MarkerOptions markerB=new MarkerOptions();
                markerB.title("약현성당");
                markerB.position(new LatLng(37.559153, 126.967443));
                markerB.flat(true);
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerB);

                MarkerOptions markerC=new MarkerOptions();
                markerC.title("이명래 고약방");
                markerC.position(new LatLng(37.553615, 126.969609));
                markerC.flat(true);
                markerC.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerC);

                MarkerOptions markerD=new MarkerOptions();
                markerD.title("충정각");
                markerD.position(new LatLng(37.560497, 126.963636));
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
                        intent.putExtra("courseTitle","중림충정 코스");
                        intent.putExtra("road1","염천교 수제화거리");
                        intent.putExtra("road2","약현성당");
                        intent.putExtra("road3","이명래 고약방");
                        intent.putExtra("road4","충정각");

                        intent.putExtra("road1x",37.559516);
                        intent.putExtra("road1y",126.970239);

                        intent.putExtra("road2x",37.559153);
                        intent.putExtra("road2y",126.967443);

                        intent.putExtra("road3x",37.553615);
                        intent.putExtra("road3y",126.969609);

                        intent.putExtra("road4x",37.560497);
                        intent.putExtra("road4y",126.963636);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                    }
                });
            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recycler);

        members.add(new ListViewMember("step.01","염천교 수제화거리",R.drawable.img_course_02_01,"경성역의 발달과 함께 피혁 밀거래가 활발했던 곳에 구두 수선가게가 생겨나고, 해방 이후 미군들의 전투화를 수선해서 신사화를 만드는 가게가 늘어나면서 시작된 우리나라 최초의 수제화거리입니다. 주로 1층에는 상가, 2~4층은 구두 만드는 공장이 위치해 소매부터 도매까지 이루어지던 곳이었습니다. 현재는 경기침체와 값싼 중국 제품에 밀려 댄스화와 같은 특수화를 주로 판매하고 있습니다."));
        members.add(new ListViewMember("step.02","약현성당",R.drawable.img_course_02_02,"1891년 서소문이 내려다 보이는 약현이라는 언덕 위에 세워진 성당으로, 명동성당에서 분리되어 서울에서 두 번째로 설립된 본당입니다. 사적 제252호로 지정된 한국 최초의 서양식 벽돌 건축물로 프랑스 출신의 코스트 신부가 설계하고 중국인 기술사에 의해 지어졌습니다. 안타깝게도 1998년에 방화로 인한 화재로 성당 내부가 소실되어 현재 성당 건물은 2000년에 복원된 모습입니다. "));
        members.add(new ListViewMember("step.03","이명래 고약방",R.drawable.img_course_02_03,"이명래 고약은 1906년 프랑스 선교사인 드비즈 신부로부터 서양 약학을 배운 고 이명래 선생(1850-1952)이 한방의서를 바탕으로 개발한 종기약입니다. 당시 의약 환경이 열악하여 종기로 죽는 사람들도 있었기 때문에, 이명래 고약은 집집마다 상비약으로 준비되어 있었다고 합니다. 이명래 선생이 돌아가신 후 선생의 둘째 사위에 의해 명래한의원으로 운영되다가 현재는 호프집으로 운영되고 있습니다."));
        members.add(new ListViewMember("step.04","충정각",R.drawable.img_course_02_04,"충정각은 남문 밖 복숭아골 세브란스 의전을 설계한 캐나다 건축가 헨리 볼드 고든이 1910년대 설계한 주택입니다. 유럽, 일본 그리고 한국의 건축 양식이 복합적으로 사용된 독특한 형태의 고택입니다. 이 집의 주인은 개화기 미국에서 기술을 전수하러 온 한성 전기회사 직원 멕렐란이었습니다. 현재는 고즈넉한 분위기의 뜰을 품은 레스토랑 겸 갤러리로 많은 사람들의 사랑을 받고 있습니다."));
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