package com.prohua.dovedemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Deep on 2018/6/5 0005.
 */

public class ImgBean implements Serializable {

    private List<String> accessory_ids;

    public List<String> getAccessory_ids() {
        return accessory_ids;
    }

    public void setAccessory_ids(List<String> accessory_ids) {
        this.accessory_ids = accessory_ids;
    }
}
