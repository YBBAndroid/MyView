package com.ybb.testview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by ybb on 2018/3/6.
 */

public class Linear extends LinearLayout {
    public Linear(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Linear(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

         int widthmode=     MeasureSpec.getMode(widthMeasureSpec);
         int widthwidth=    MeasureSpec.getSize(widthMeasureSpec);
         int heightmode=    MeasureSpec.getMode(heightMeasureSpec);
         int heightsize=    MeasureSpec.getSize(heightMeasureSpec);

        int width=0;
        int height=0;
        int childcount=getChildCount();

        for (int i=0;i<childcount;i++){
            View childview=getChildAt(i);
            MarginLayoutParams params= (MarginLayoutParams) childview.getLayoutParams();
            measureChild(childview,widthMeasureSpec,heightMeasureSpec);

            int childwidth= childview.getMeasuredWidth();
            int childheight =childview.getMeasuredHeight()+params.topMargin;
            height+=childheight;
            width=  Math.max(childwidth,width);
        }
        setMeasuredDimension((widthmode==MeasureSpec.EXACTLY)?widthwidth:width,
                (heightmode==MeasureSpec.EXACTLY)?heightsize:height);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
          int child=getChildCount();
          int top=0;
            for (int k=0;k<child;k++){
                View childView=getChildAt(k);
                MarginLayoutParams params= (MarginLayoutParams) childView.getLayoutParams();
                int childwidth= childView.getMeasuredWidth();
                int childheight=childView.getMeasuredHeight()+params.topMargin;
                childView.layout(0,top,childwidth,top+childheight);
                top+=childheight;
            }
    }

    @Override

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }
}
