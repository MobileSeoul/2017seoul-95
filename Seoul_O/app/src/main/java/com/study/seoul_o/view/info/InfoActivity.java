package com.study.seoul_o.view.info;

import android.Manifest;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.study.seoul_o.R;
import com.study.seoul_o.data.Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    private ViewPager mBannerViewPager;
    private BannerPagerAdapter mBannerPagerAdapter;
    private LinearLayout mBannerDotsLayout;
    private TypedArray mBannerArray;
    private int numberOfBannerImage;
    private View[] mBannerDotViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // 주소 링크
        addressInit();

        // 다산 콜 , 전화 권한 허가
        permissionCheck();
        dasanCallInit();

         /*===================Inetelizing Banner Variables===============*/
        mBannerArray = getResources().obtainTypedArray(R.array.banner_img_array);
        numberOfBannerImage=mBannerArray.length();
        mBannerDotViews = new View[numberOfBannerImage]; // create an empty array;

        /*===================Banner Pager Configuration=================*/
        mBannerViewPager = (ViewPager) findViewById(R.id.bannerViewPager);
        mBannerDotsLayout= (LinearLayout) findViewById(R.id.bannerDotsLayout);
        mBannerPagerAdapter=new BannerPagerAdapter(InfoActivity.this, mBannerArray);
        mBannerViewPager.setAdapter(mBannerPagerAdapter);


        /*===========================START Banner Configuration Code ======================================*/

        for (int i = 0; i < numberOfBannerImage; i++) {
            // create a new textview
            final View bannerDotView = new View(this);
/*Creating the dynamic dots for banner*/
            LinearLayout.LayoutParams dotLayoutParm=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dotLayoutParm.height = getResources().getDimensionPixelSize(R.dimen.standard_10);
            dotLayoutParm.width = getResources().getDimensionPixelSize(R.dimen.standard_10);
            dotLayoutParm.setMargins(getResources().getDimensionPixelSize(R.dimen.standard_8),0,0,0);
            bannerDotView.setLayoutParams(dotLayoutParm);
            bannerDotView.setBackground(getResources().getDrawable(R.drawable.shape_deselected_dot));

            // add the textview to the linearlayout
            mBannerDotsLayout.addView(bannerDotView);


            // save a reference to the textview for later
            mBannerDotViews[i] = bannerDotView;
        }

        AutoSwipeBanner();
        mBannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeDotBG(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
/*===========================END Banner Configuration Code ======================================*/

    }

    public void AutoSwipeBanner(){
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                int currentPage=mBannerViewPager.getCurrentItem();
                if (currentPage == numberOfBannerImage-1) {
                    currentPage = -1;
                }
                mBannerViewPager.setCurrentItem(currentPage+1, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);

    }

    private void changeDotBG(int position){

        for(int i = 0; i < numberOfBannerImage; i++){
            if(position==i){
                mBannerDotViews[i].setBackground(getResources().getDrawable(R.drawable.shape_selected_dot));
            }else{
                mBannerDotViews[i].setBackground(getResources().getDrawable(R.drawable.shape_deselected_dot));
            }

        }


        //지도 클릭
        ImageView map=(ImageView)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(getApplicationContext(), MapActivity.class);
                startActivity(myIntent);
            }
        });

//        ImageView poster=(ImageView)findViewById(R.id.test);
//
//        //서버통신으로 글라이드하기
//        Glide.with(getApplicationContext())
//                .load("http://seoul-o.run.goorm.io/user/image/getBestImages")
//                //.bitmapTransform(new CropCircleTransformation(bitmapPool))
//                //.override(130,130)
//                .into(poster);


    }




    private void addressInit(){
        TextView addressTextView = (TextView) findViewById(R.id.info_address_textview);
        addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Data.load_url));
                startActivity(i);
            }
        });
    }

    private void dasanCallInit(){
        TextView dasancallTextView = (TextView) findViewById(R.id.dasancall_textview);
        dasancallTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:"+Data.dasan_call)));
            }
        });
    }

    private void permissionCheck(){
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("전화 권한 허가\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(InfoActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(InfoActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }


    };

}
