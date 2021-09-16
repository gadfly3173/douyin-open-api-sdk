package vip.gadfly.tiktok.open.base.entity;

import java.io.Serializable;

public class TiktokOpenStaDataItemBase implements Serializable {

    private Object item;

    private Integer value;

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
