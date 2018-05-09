package com.ybb.testview.RecylerView.ChatUI;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by ybb on 2018/3/20.
 */

public class MultipleItem implements MultiItemEntity{
    public static final int  SEND = 1;
    public static final int  Accetp= 2;

    private int itemType;
    private String icon="";
    private String content="";

    public MultipleItem(int itemType,String icon,String content){
        this.itemType=itemType;
        this.icon=icon;
        this.content=content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    @Override
    public int getItemType() {
        return itemType;
    }
}
