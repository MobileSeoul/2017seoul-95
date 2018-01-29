package com.study.seoul_o.view.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

import com.study.seoul_o.R;

/**
 * Created by yjk on 2017. 8. 29..
 */

public class LoadingDialog extends Dialog {
    AnimationDrawable animationDrawable;

    public LoadingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.dialog_loading);

        ImageView loadingImageView =(ImageView) findViewById(R.id.loading_imageview);
        animationDrawable = (AnimationDrawable)loadingImageView.getBackground();
        animationDrawable.start();

    }

    @Override
    public void onDetachedFromWindow() {
        animationDrawable.stop();
        super.onDetachedFromWindow();
    }
}
