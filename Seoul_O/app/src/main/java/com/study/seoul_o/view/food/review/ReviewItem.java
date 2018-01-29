package com.study.seoul_o.view.food.review;

/**
 * Created by yjk on 2017. 10. 28..
 */

public class ReviewItem {
    private String id;
    private String content;
    private String star;

    public ReviewItem(String id, String content, String star){
        this.id = id;
        this.content = content;
        this.star = star;
    }

    public String getId(){
        return id;
    }
    public String getContent(){
        return  content;
    }

    public float getStar() {
        return Float.parseFloat(star);
    }
}
