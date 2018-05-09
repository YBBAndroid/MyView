package com.ybb.testview.View.RuningTextView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ybb.testview.R;

import java.text.DecimalFormat;

/**
 * Created by ybb on 2018/4/23.
 */

public class NumberRunningTextView extends TextView {
    private int frameNum; //<!--帧数-->
    private int textType;//<!--内容格式-->
    private boolean useCommaFormat;//<!--是否使用第三位数字逗号-->
    private boolean runWhenchange; // <!--是否当内容改变时使用动画,不改变时不使用动画-->
    private static final int MONEY_TYPE = 0; //金钱类型
    private String preStr; //上一次内容

    private DecimalFormat formatter = new DecimalFormat("0.00");// 格式化金额，保留两位小数

    private double finalMoneyNum;// 目标金额数字（最终的金额数字）
    private int finalNum;//目标数字（最终的数字）

    public NumberRunningTextView(Context context) {
        this(context, null);
    }

    public NumberRunningTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textStyle);
    }

    public NumberRunningTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberRunningTextView);
        frameNum = ta.getInt(R.styleable.NumberRunningTextView_frameNum, 30);
        textType = ta.getInt(R.styleable.NumberRunningTextView_textType, MONEY_TYPE);
        useCommaFormat = ta.getBoolean(R.styleable.NumberRunningTextView_userCommaformat, true);
        runWhenchange = ta.getBoolean(R.styleable.NumberRunningTextView_runWhenchange, true);
        ta.recycle();

    }

    public void setContent(String str) {

        if (runWhenchange) {  //判断是否当内容改变时使用动画
            if (TextUtils.isEmpty(preStr)) { //第一次进来
                preStr = str;                //保存当前str
                userAnimByType(str);
                return;
            }
            if (preStr.equals(str)) { //如果两次内容一致不作处理
                return;
            }
            preStr = str;
        }
        userAnimByType(str);
    }

    private void userAnimByType(String str) {
        if (textType == MONEY_TYPE) {
            playMoneyAnim(str);
        } else {
            playNumAnim(str);
        }
    }

    /*  金钱模式*/
    private void playMoneyAnim(String str) {
        String money = str.replace(",", "").replace("-", "");
        finalMoneyNum = Double.parseDouble(money);//字符串转换成double
        if (finalMoneyNum == 0) {
            NumberRunningTextView.this.setText(str);
            return;
        }
        final double size = finalMoneyNum / frameNum; //每帧大小
        ValueAnimator animator = ValueAnimator.ofInt(0, frameNum);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                double current = (int) valueAnimator.getAnimatedValue();
                double str = current * size;
                String result= formatter.format(str).toString();//保留两位小数的字符串
                if (useCommaFormat) {
                    String formatStr = formatTosepara(Double.parseDouble(result));//使用每三位数字一个逗号的格式
                    NumberRunningTextView.this.setText(formatStr);
                } else {
                    NumberRunningTextView.this.setText(result);
                }
            }
        });
        animator.start();

    }

  /* 普通数字模式*/
    private void playNumAnim(String str) {
        String num = str.replace(",", "").replace("-", "");
        finalNum = Integer.parseInt(num);
        if (finalNum < frameNum) {  //如果设定值小于帧数值,直接赋值
            NumberRunningTextView.this.setText(String.valueOf(str));
            return;
        }
        final int size = finalNum / frameNum; //每帧大小
        final int red = finalNum % frameNum; //取余数 (防止  finalNum / frameNum 不能被整除时, 最终结果小于设定值)

        ValueAnimator animator = ValueAnimator.ofInt(0, frameNum);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();
                int str = current * size + red;
                NumberRunningTextView.this.setText(String.valueOf(str));
            }
        });
        animator.start();
    }

    public static String formatTosepara(double data) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(data);
    }
}
