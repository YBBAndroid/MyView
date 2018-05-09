package com.ybb.testview.View.FloatingView;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ybb on 2018/3/16.
 */

public class FloatingView extends BaseFloatingView {

    private boolean isShowing = false;
    private static FloatingView floatingView;
    public static FloatingView getInstance(Context context,int viewresID){
        if (floatingView==null) {
            synchronized (FloatingView.class) {
                if (floatingView == null) {
                    floatingView = new FloatingView(context, viewresID);
                }
            }
        }
        return floatingView;
    }

    public FloatingView(Context context,int viewresID) {
        super(context);
        View.inflate(context,viewresID,this);
    }

    /**
     * 显示悬浮球
     */
    public void show() {
        if (!isShowing) super.showView(this);
        isShowing = true;
    }

    /**
     * 关闭悬浮球
     */
    public void dismiss() {
        if (isShowing)
            super.hideView();
        isShowing = false;

    }

    /**
     * 单击显示popupWindow
     * @param e motionEvent
     * @return false 本身不对点击事件进行消费
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        super.onSingleTapUp(e);
        Log.i("ddddddd","点击了");
        return false;
    }


}
