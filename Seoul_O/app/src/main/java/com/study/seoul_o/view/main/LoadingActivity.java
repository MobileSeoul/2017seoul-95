package com.study.seoul_o.view.main;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.study.seoul_o.R;

public class LoadingActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        startAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2700);
    }

    private void startAnim(){
        ImageView loadingImageView =(ImageView) findViewById(R.id.loading_imageview);
        animationDrawable = (AnimationDrawable)loadingImageView.getBackground();
        animationDrawable.start();
    }

    @Override
    protected void onDestroy() {
        animationDrawable.stop();
        super.onDestroy();
    }
}
