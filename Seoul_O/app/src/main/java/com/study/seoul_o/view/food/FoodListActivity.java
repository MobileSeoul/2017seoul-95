package com.study.seoul_o.view.food;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.study.seoul_o.R;
import com.study.seoul_o.view.common.LoadingDialog;
import com.study.seoul_o.view.info.InfoActivity;
import com.study.seoul_o.view.instar.InstarActivity;

public class FoodListActivity extends AppCompatActivity {
    public final String TITLE = "title";
    private FoodListButton f1,f2_1,f2_2,f3_1,f3_2,f4_1,f4_3,f5_1,f5_3,f6_1,f6_3,f7_1,f7_3,f8_1,f8_2,f9_1,f9_3,f10_1,f10_2;  //리스트 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        init();

        tab_bar();
    }



    private void init(){ //버튼 생성

        ButtonClickListener listener = new ButtonClickListener();


        f2_1 = (FoodListButton) findViewById(R.id.food_list_button02_1);
        f2_1.setOnClickListener(listener);
        f2_2 = (FoodListButton) findViewById(R.id.food_list_button02_2);
        f2_2.setOnClickListener(listener);
        f3_1 = (FoodListButton) findViewById(R.id.food_list_button03_1);
        f3_1.setOnClickListener(listener);
        f3_2 = (FoodListButton) findViewById(R.id.food_list_button03_2);
        f3_2.setOnClickListener(listener);
        f4_1 = (FoodListButton) findViewById(R.id.food_list_button04_1);
        f4_1.setOnClickListener(listener);
        f4_3 = (FoodListButton) findViewById(R.id.food_list_button04_3);
        f4_3.setOnClickListener(listener);
        f5_1 = (FoodListButton) findViewById(R.id.food_list_button05_1);
        f5_1.setOnClickListener(listener);
        f5_3 = (FoodListButton) findViewById(R.id.food_list_button05_3);
        f5_3.setOnClickListener(listener);
        f6_1 = (FoodListButton) findViewById(R.id.food_list_button06_1);
        f6_1.setOnClickListener(listener);
        f6_3 = (FoodListButton) findViewById(R.id.food_list_button06_3);
        f6_3.setOnClickListener(listener);
        f7_1 = (FoodListButton) findViewById(R.id.food_list_button07_1);
        f7_1.setOnClickListener(listener);
        f7_3 = (FoodListButton) findViewById(R.id.food_list_button07_3);
        f7_3.setOnClickListener(listener);
        f8_1 = (FoodListButton) findViewById(R.id.food_list_button08_1);
        f8_1.setOnClickListener(listener);
        f8_2 = (FoodListButton) findViewById(R.id.food_list_button08_2);
        f8_2.setOnClickListener(listener);
        f9_1 = (FoodListButton) findViewById(R.id.food_list_button09_1);
        f9_1.setOnClickListener(listener);
        f9_1 = (FoodListButton) findViewById(R.id.food_list_button09_3);
        f9_1.setOnClickListener(listener);
        f10_1 = (FoodListButton) findViewById(R.id.food_list_button10_1);
        f10_1.setOnClickListener(listener);
        f10_2 = (FoodListButton) findViewById(R.id.food_list_button10_2);
        f10_2.setOnClickListener(listener);
    }

    private void tab_bar() { //tab bar 메소드

        TabHost tabHost1 =(TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        //첫 번째 tab
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("전 체");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("한 식");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("양 식");
        tabHost1.addTab(ts3);

    }

    class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String title = ((FoodListButton)view).getTitleText();

            Intent i = new Intent(FoodListActivity.this, FoodDetailActivity.class);
            i.putExtra(TITLE,title);
            overridePendingTransition(R.anim.right_in_anim,R.anim.stop_anim);
            startActivity(i);
        }
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

}
