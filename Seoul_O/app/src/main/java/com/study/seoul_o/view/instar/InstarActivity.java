package com.study.seoul_o.view.instar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import com.study.seoul_o.R;
import com.study.seoul_o.data.Data;
import com.study.seoul_o.view.instar.cards.SliderAdapter;
import com.study.seoul_o.view.main.MainActivity;

public class InstarActivity extends AppCompatActivity {
    public static final String TAG = "### InstarActivity";
    public static final String title = "Instar";

    private InstarData instarData;

    private TextSwitcher idTextSwitcher;
    private TextSwitcher tagTextSwitcher;
    private TextSwitcher contentTextSwitcher;

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;

    private int currentPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instar);

        init();

        initRecyclerView();
        initSwitcher();
    }


    private void init(){
        instarData = new InstarData();

        idTextSwitcher = (TextSwitcher) findViewById(R.id.instar_id);
        tagTextSwitcher = (TextSwitcher) findViewById(R.id.instar_tag);
        contentTextSwitcher = (TextSwitcher) findViewById(R.id.instar_content);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {

        SliderAdapter sliderAdapter = new SliderAdapter(instarData.images, 4, new OnCardClickListener());


        recyclerView.setLayoutManager(new CardSliderLayoutManager(50, 300, 15));

        new CardSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });
        recyclerView.setAdapter(sliderAdapter);

//
//        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();
//        layoutManger.setMeasuredDimension(600,300);
//
//        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void initSwitcher(){
        idTextSwitcher.setFactory(new TextViewFactory(R.style.IdTextView, false));
        idTextSwitcher.setCurrentText(instarData.id[0]);

        tagTextSwitcher.setFactory(new TextViewFactory(R.style.TagTextView, false));
        tagTextSwitcher.setCurrentText(instarData.tag[0]);

        contentTextSwitcher.setFactory(new TextViewFactory(R.style.ContentTextView, false));
        contentTextSwitcher.setCurrentText(instarData.content[0]);
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        idTextSwitcher.setInAnimation(InstarActivity.this, animH[0]);
        idTextSwitcher.setOutAnimation(InstarActivity.this, animH[1]);
        idTextSwitcher.setText(instarData.id[pos]);

        tagTextSwitcher.setInAnimation(InstarActivity.this, animV[0]);
        tagTextSwitcher.setOutAnimation(InstarActivity.this, animV[1]);
        tagTextSwitcher.setText(instarData.tag[pos]);

        contentTextSwitcher.setInAnimation(InstarActivity.this, animV[0]);
        contentTextSwitcher.setOutAnimation(InstarActivity.this, animV[1]);
        contentTextSwitcher.setText(instarData.content[pos]);

        currentPosition = pos;
    }


    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
//            if (clickedPosition == activeCardPosition) {
//                final Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, pics[activeCardPosition % pics.length]);
//
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                    startActivity(intent);
//                } else {
//                    final CardView cardView = (CardView) view;
//                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
//                    final ActivityOptions options = ActivityOptions
//                            .makeSceneTransitionAnimation(MainActivity.this, sharedView, "shared");
//                    startActivity(intent, options.toBundle());
//                }
//            } else if (clickedPosition > activeCardPosition) {
//                recyclerView.smoothScrollToPosition(clickedPosition);
//                onActiveCardChange(clickedPosition);
//            }
        }
    }



    private class TextViewFactory implements  ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(InstarActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(InstarActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }


}
