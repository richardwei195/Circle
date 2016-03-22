package com.onemore.circle.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by jiangwei on 16/3/17.
 */
public class ChallengeCup extends BmobObject {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
