package com.ybb.testview.View.WareView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by ybb on 2018/2/28.
 * 水波纹
 */

public class WaveView extends View {
    Paint paint;
    Path path=new Path();
    int  itemwarelen=400; //波长
    int  originY = 300;
    int  dx; //移动距离
    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public WaveView(Context context) {
        super(context);
        initPaint();
    }


    public void initPaint(){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(2);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        int halrlen=itemwarelen/2; //波长一半
        path.moveTo(-itemwarelen+dx,originY);
        for (int i=-itemwarelen;i<getWidth()+itemwarelen;i+=itemwarelen){
            path.rQuadTo(halrlen/2,-50,halrlen,0);
            path.rQuadTo(halrlen/2,50,halrlen,0);
        }
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path,paint);
    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,itemwarelen);
        animator.setDuration(2000); //动画的时间(水波滚动的速度)
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    public void reset(){
        paint.reset();
        invalidate();
    }


}
