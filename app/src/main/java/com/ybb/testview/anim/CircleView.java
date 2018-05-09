package com.ybb.testview.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ybb on 2018/3/8.
 */

public class CircleView extends View {

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
        invalidate();
    }

    int Radius=100;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.drawCircle(300,300,getRadius(),paint);
    }



}
