package com.study.seoul_o.view.food;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.seoul_o.R;


/**
 * Created by yjk on 2017. 10. 22..
 */

public class FoodListButton extends LinearLayout {

    ImageView imageView;
    TextView textView;

    public FoodListButton(Context context) {
        super(context);
        initView();
    }

    public FoodListButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public FoodListButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs);

    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.layout_list_button, this, false);
        addView(v);

        imageView = (ImageView) findViewById(R.id.listbutton_imageview);
        textView = (TextView) findViewById(R.id.listbutton_textview);

    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomListBtn);

        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        int background = typedArray.getResourceId(R.styleable.CustomListBtn_customlistbackground, 0);
        imageView.setBackgroundResource(background);

        String text_string = typedArray.getString(R.styleable.CustomListBtn_customlisttext);
        textView.setText(text_string);


        typedArray.recycle();

    }

    public String getTitleText(){
        return textView.getText().toString();
    }


}
