package com.study.seoul_o.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.study.seoul_o.R;
import com.study.seoul_o.view.course.CourceListActivity;
import com.study.seoul_o.view.food.FoodListActivity;
import com.study.seoul_o.view.info.InfoActivity;
import com.study.seoul_o.view.instar.InstarActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainButton01,mainButton02,mainButton03,mainButton04;

    boolean isShow = false;
    int scrollRange = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        init();
    }

    private void init(){
        mainButton01 = (LinearLayout) findViewById(R.id.mainButton01);
        mainButton02 = (LinearLayout) findViewById(R.id.mainButton02);
        mainButton03 = (LinearLayout) findViewById(R.id.mainButton03);
        mainButton04 = (LinearLayout) findViewById(R.id.mainButton04);

        /**
         * 서울로 정보
         */
        mainButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, InfoActivity.class);
                overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                startActivity(i);
            }
        });


        /**
         * 코스
         */
        mainButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, CourceListActivity.class);
                overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                startActivity(i);
            }
        });


        /**
         * 맛집
         */
        mainButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, FoodListActivity.class);
                overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                startActivity(i);
            }
        });

        /**
         * 인스타 버튼
         */
        mainButton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, InstarActivity.class);
                overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
                startActivity(i);
            }
        });

    }








}
