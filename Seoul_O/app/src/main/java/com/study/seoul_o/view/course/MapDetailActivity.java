package com.study.seoul_o.view.course;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.study.seoul_o.R;

public class MapDetailActivity extends AppCompatActivity {

    Toolbar toolbar;

    GoogleMap map;

    String courseTitle,road1,road2,road3,road4;

    double road1x, road1y,road2x, road2y,road3x, road3y,road4x, road4y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_detail);

        Intent intent = getIntent();

        courseTitle = intent.getStringExtra("courseTitle");
        road1 = intent.getStringExtra("road1");
        road2 = intent.getStringExtra("road2");
        road3 = intent.getStringExtra("road3");
        road4 = intent.getStringExtra("road4");

        road1x=intent.getDoubleExtra("road1x",0);
        road1y=intent.getDoubleExtra("road1y",0);

        road2x=intent.getDoubleExtra("road2x",0);
        road2y=intent.getDoubleExtra("road2y",0);

        road3x=intent.getDoubleExtra("road3x",0);
        road3y=intent.getDoubleExtra("road3y",0);

        road4x=intent.getDoubleExtra("road4x",0);
        road4y=intent.getDoubleExtra("road4y",0);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(courseTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FragmentManager manager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) manager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(road1x, road1y), 15));

                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(road1x, road1y)).title(road1));
                marker.showInfoWindow();

                MarkerOptions markerA = new MarkerOptions();
                markerA.title(road1);
                markerA.position(new LatLng(road1x, road1y));
                markerA.flat(true);
                markerA.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerA);

                MarkerOptions markerB = new MarkerOptions();
                markerB.title(road2);
                markerB.position(new LatLng(road2x, road2y));
                markerB.flat(true);
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerB);

                MarkerOptions markerC = new MarkerOptions();
                markerC.title(road3);
                markerC.position(new LatLng(road3x, road3y));
                markerC.flat(true);
                markerC.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerC);

                MarkerOptions markerD = new MarkerOptions();
                markerD.title(road4);
                markerD.position(new LatLng(road4x, road4y));
                markerD.flat(true);
                markerD.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerD);


                map.addPolyline(new PolylineOptions().geodesic(true)
                        .add(new LatLng(road1x, road1y))
                        .add(new LatLng(road2x, road2y))
                        .add(new LatLng(road3x, road3y))
                        .add(new LatLng(road4x, road4y))
                );

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                map.setMyLocationEnabled(true);


                UiSettings settings=map.getUiSettings();
                settings.setZoomControlsEnabled(true);

            }
        });
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
}