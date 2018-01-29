package com.study.seoul_o.view.info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import com.study.seoul_o.R;

import uk.co.senab.photoview.PhotoViewAttacher;


public class MapActivity extends AppCompatActivity {


    ImageView m_imageview;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        m_imageview=(ImageView)findViewById(R.id.imageView);
        mAttacher=new PhotoViewAttacher(m_imageview);

    }

}