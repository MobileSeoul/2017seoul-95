package com.study.seoul_o.view.instar;

import com.study.seoul_o.R;

/**
 * Created by yjk on 2017. 10. 22..
 */

public class InstarData {
    int[] images;
    String[] id;
    String[] tag;
    String[] content;

    public InstarData(){
        images = new int[]{R.drawable.course_01_01,
                            R.drawable.course_01_02,
                            R.drawable.course_01_03,
                            R.drawable.course_01_04};

        id = new String[] {"jong123",
                            "chlehdcks8810",
                            "Lee0189",
                            "LGS0051"};

        tag = new String[] {"#서울로7017",
                            "#서울로 개꿀",
                            "#볼거없넹",
                            "#서울로 #우파루파"};

        content = new String[] {"서울고가도로 와봤다 경치 좋네",
                                "재밌다 ㅋㅋ",
                                "생각보다 볼거 없네요",
                                "우파루파"};

    }


}
