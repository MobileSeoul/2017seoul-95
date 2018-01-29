package com.study.seoul_o.view.course;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.study.seoul_o.R;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourceListActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffffff"));
        toolbar.setTitle("도보 관광 코스");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void clickBtn(View v){
        Intent intent=new Intent(this,DetailActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
    }

    public void clickBtn2(View v){
        Intent intent=new Intent(this,DetailActivity2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
    }

    public void clickBtn3(View v){
        Intent intent=new Intent(this,DetailActivity3.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
    }

    public void clickBtn4(View v){
        Intent intent=new Intent(this,DetailActivity4.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
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

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
        super.onBackPressed();
    }
}