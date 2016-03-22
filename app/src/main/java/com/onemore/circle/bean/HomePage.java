package com.onemore.circle.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by jiangwei on 16/3/16.
 */
public class HomePage extends BmobObject {

    private String Title;
    private BmobFile advertisement;

    public BmobFile getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(BmobFile advertisement) {
        this.advertisement = advertisement;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
