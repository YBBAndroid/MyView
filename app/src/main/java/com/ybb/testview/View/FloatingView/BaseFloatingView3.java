package com.ybb.testview.View.FloatingView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 跟随手指移动View以及显示/隐藏的接口封装
 */
public abstract class BaseFloatingView3 extends FrameLayout implements GestureDetector.OnGestureListener {
    private Scroller scroller;
    protected final Context mContext;
    protected WindowManager mWindowManager;
    private GestureDetector mGestureDetector;
    private WindowManager.LayoutParams layoutParams;
    public  float lastX, lastY;

    public BaseFloatingView3(Context context) {
        super(context);

        this.mContext = context;

        this.mGestureDetector = new GestureDetector(context, this);
        scroller=new Scroller(context);

    }

    public BaseFloatingView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mGestureDetector = new GestureDetector(context, this);
    }

    public BaseFloatingView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mGestureDetector = new GestureDetector(context, this);
    }

    protected void showView(View view) {
        showView(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    protected void showView(View view, int width, int height) {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //TYPE_TOAST仅适用于4.4+系统，假如要支持更低版本使用TYPE_SYSTEM_ALERT(需要在manifest中声明权限)
        //7.1（包含）及以上系统对TYPE_TOAST做了限制
        int type = WindowManager.LayoutParams.TYPE_TOAST;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams = new WindowManager.LayoutParams(type);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        //layoutParams.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; //no limit适用于超出屏幕的情况，若添加此flag需要增加边界检测逻辑
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        mWindowManager.addView(view, layoutParams);
    }

    protected void hideView() {
        if (null != mWindowManager)
            mWindowManager.removeViewImmediate(this);
        mWindowManager = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        lastX = e.getRawX();
        lastY = e.getRawY();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float nowX, nowY, tranX, tranY;
        // 获取移动时的X，Y坐标
        nowX = e2.getRawX();
        nowY = e2.getRawY();
        // 计算XY坐标偏移量
        tranX = nowX - lastX;
        tranY = nowY - lastY;
        // 移动悬浮窗
        layoutParams.x += tranX;
        layoutParams.y += tranY;
        //更新悬浮窗位置
        mWindowManager.updateViewLayout(this, layoutParams);
        //记录当前坐标作为下一次计算的上一次移动的位置坐标
        lastX = nowX;
        lastY = nowY;
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //Toast.makeText(mContext, "onLongPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int sreenWith=getSreenWidth();
        float curX=e2.getRawX();
        float curY=e2.getRawY();
        Log.i("floating onflint==","curX="+curX+" curY="+curY);
        //左边
        if (curX<sreenWith/2){
            scroller.startScroll((int) curX,(int)curY,0,(int)curY);
        }else {//右边
            scroller.startScroll((int) curX,(int)curY,sreenWith,(int)curY);
        }
        invalidate();
        return false;
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            float currX = scroller.getCurrX(); //currX 为每次移动一小段的距离
          //  scrollTo((int) currX,0);

            layoutParams.x = (int) currX;
            layoutParams.y = scroller.getCurrY();
            //更新悬浮窗位置
            mWindowManager.updateViewLayout(this, layoutParams);
            invalidate(); //递归调用
        }
    }

    public int getSreenWidth(){
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width;
    }
    public int getSreenHeight(){
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return height;
    }
}
