package com.ybb.testview.View.PaintView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ybb on 2018/3/5.
 * 画画板
 */

public class PaintView extends View {
    Paint paint;
    Path path=new Path();
    float preX,preY;

    public PaintView(Context context) {
        super(context);
        initPaint();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            {
                path.moveTo(event.getX(),event.getY());
                preX=event.getX();
                preY=event.getY();
                return true;
            }

            case MotionEvent.ACTION_MOVE:
            {

                float endX=(preX+event.getX())/2;
                float endY=(preY+event.getY())/2;
                path.quadTo(preX,preY,endX,endY);
                preX=event.getX();
                preY=event.getY();
                postInvalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    public void initPaint(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);

    }

    //重置
    public void reset(){
        paint.reset();
        invalidate();
    }
}
