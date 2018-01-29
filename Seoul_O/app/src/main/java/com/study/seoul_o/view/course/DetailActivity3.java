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
import android.widget.ListView;

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

public class DetailActivity3 extends AppCompatActivity {

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
        setContentView(R.layout.activity_course_detail03);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("청파효창 코스");
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

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.552942, 126.968235), 14));

                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(37.552942, 126.968235)).title("국립극단"));
                marker.showInfoWindow();

                MarkerOptions markerA=new MarkerOptions();
                markerA.title("국립극단");
                markerA.position(new LatLng(37.552942, 126.968235));
                markerA.flat(true);
                markerA.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerA);

                MarkerOptions markerB=new MarkerOptions();
                markerB.title("개미슈퍼");
                markerB.position(new LatLng(37.552084, 126.966894));
                markerB.flat(true);
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerB);

                MarkerOptions markerC=new MarkerOptions();
                markerC.title("성우이용원");
                markerC.position(new LatLng(37.549673, 126.963332));
                markerC.flat(true);
                markerC.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerC);

                MarkerOptions markerD=new MarkerOptions();
                markerD.title("효창공원");
                markerD.position(new LatLng(37.545085, 126.960316));
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
                        intent.putExtra("courseTitle","청파효창 코스");
                        intent.putExtra("road1","국립극단");
                        intent.putExtra("road2","개미슈퍼");
                        intent.putExtra("road3","성우이용원");
                        intent.putExtra("road4","효창공원");

                        intent.putExtra("road1x",37.552942);
                        intent.putExtra("road1y",126.968235);

                        intent.putExtra("road2x",37.552084);
                        intent.putExtra("road2y",126.966894);

                        intent.putExtra("road3x",37.549673);
                        intent.putExtra("road3y",126.963332);

                        intent.putExtra("road4x",37.545085);
                        intent.putExtra("road4y",126.960316);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                    }
                });
            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recycler);

        members.add(new ListViewMember("step.01","국립극단",R.drawable.img_course_03_01,"\n" +
                "이곳은 1981년부터 약 30년간 기무사 수송대로 사용했던 곳입니다 . 기무사란 국군 기무사사령부의 약자로 군 정보수사기관입니다. 보안이 철통 같던 시절에 근접한 지역에서 기무사의 담을 넘는 집은 지을 수 없었고 , 때문에 동네 곳곳에 오래된 집들이 현재까지 남아 있습니다.\n" +
                "\n"));
        members.add(new ListViewMember("step.02","개미슈퍼",R.drawable.img_course_03_02,"이곳은 100년이 넘는 시간 동안 자리를 지키고 있는 동네 슈퍼입니다. 처음에는 작은 마당이 있는 도시형 한옥집을 개조해서 담배나 딱지를 팔던 구멍가게로 시작했습니다. 나중에는 방을 확장하고 유리문을 달아서 1층 공간은 슈퍼로 만들고 2층은 집주인 아저씨께서 손수 지어 한 때는 댄스 교실로 사용했습니다. 내부에는 아궁이가 있었던 부엌에 연탄보일러를 놓고 사용하다 이후 싱크대가 있는 현대식 부엌으로 개조한 흔적이 고스란히 남아 있습니다."));
        members.add(new ListViewMember("step.03","성우이용원",R.drawable.img_course_03_03,"성우이용원은 1927년부터 지금까지, 약 90년간 한 자리를 지킨 서울에서 가장 오래된 이용원입니다. 지금은 1대 서재덕 씨의 사위인 2대 이성순 씨의 아들 이남열 씨가 현재까지 이용원을 지키고 있습니다. 성우이용원은 옛 이발 방식을 선호하는 단골들을 위해 아직 면도칼을 손수 갈고 식초로 머리를 감는 등 옛 방식을 고스란히 이어오고 있습니다. 지금은 그 생활사적 가치를 인정받아 서울미래유산으로 지정되었습니다. "));
        members.add(new ListViewMember("step.04","효창공원",R.drawable.img_course_03_04,"효창공원은 원래 어린 나이에 세상을 등진 정조의 첫째아들 문효세자와 몇달 후 죽은 그의 어머니의 묘소가 있던 ‘효창원'이었으나 일제 강점기 두 무덤이 서삼릉으로 강제 이장이 되면서 현재처럼 효창공원이라는 이름을 갖게 되었습니다. 이후 김구의 묘소를 비롯한 대한민국 임시정부 핵심 인물들의 묘소가 조성되었습니다. 역사가 오래된 공원이기 때문에 삼림이 울창해 새로 생긴 공원들과는 다른 매력을 가진 공원입니다."));
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