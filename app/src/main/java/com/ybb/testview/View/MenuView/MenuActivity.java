package com.ybb.testview.View.MenuView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ybb.testview.R;

/**
 * Created by ybb on 2018/3/9.
 * 查考文档:http://blog.csdn.net/harvic880925/article/details/50763286
 * 路径菜单
 */

public class MenuActivity extends Activity implements View.OnClickListener{
    public Button btnMenu,btnMenuItem1,btnMenuItem2,btnMenuItem3,btnMenuItem4,btnMenuItem5;
    public boolean mIsMenuOpen=false;

    public int radius=300; //半径
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMenu= (Button) findViewById(R.id.menu);
        btnMenuItem1= (Button) findViewById(R.id.menu_item1);
        btnMenuItem2= (Button) findViewById(R.id.menu_item2);
        btnMenuItem3= (Button) findViewById(R.id.menu_item3);
        btnMenuItem4= (Button) findViewById(R.id.menu_item4);
        btnMenuItem5= (Button) findViewById(R.id.menu_item5);
        btnMenu.setOnClickListener(this);
        btnMenuItem1.setOnClickListener(this);
        btnMenuItem2.setOnClickListener(this);
        btnMenuItem3.setOnClickListener(this);
        btnMenuItem4.setOnClickListener(this);
        btnMenuItem5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view==btnMenu){
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                openMenuAnim(btnMenuItem1,0,5);
                openMenuAnim(btnMenuItem2,1,5);
                openMenuAnim(btnMenuItem3,2,5);
                openMenuAnim(btnMenuItem4,3,5);
                openMenuAnim(btnMenuItem5,4,5);
                Log.i("MenuActivity==","open");
            } else {
                mIsMenuOpen = false;
                closeMenuAnim(btnMenuItem1,0,5);
                closeMenuAnim(btnMenuItem2,1,5);
                closeMenuAnim(btnMenuItem3,2,5);
                closeMenuAnim(btnMenuItem4,3,5);
                closeMenuAnim(btnMenuItem5,4,5);
                Log.i("MenuActivity==","close");
            }
        }else {
            Log.i("MenuActivity==","非非非非非");
        }

    }



    public void openMenuAnim(View view,int index,int sum){
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree= Math.toRadians(90)/(sum-1)*index;

        int tranlateX= -(int) (Math.sin(degree)*radius);
        int tranlateY= -(int) (Math.cos(degree)*radius);

        ObjectAnimator anim1=ObjectAnimator.ofFloat(view,"translationX",0,tranlateX);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(view,"translationY",0,tranlateY);
        ObjectAnimator anim3=ObjectAnimator.ofFloat(view,"scaley",0,1);
        ObjectAnimator anim4=ObjectAnimator.ofFloat(view,"scaleX",0,1);
        ObjectAnimator anim5=ObjectAnimator.ofFloat(view,"alpha", 0f, 1);

        AnimatorSet set =new AnimatorSet();
        set.playTogether(anim1,anim2,anim3,anim4,anim5);
        set.setDuration(500);
        set.start();
    }


    public void closeMenuAnim(View view,int index,int sum){
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double radians= Math.toRadians((90)/(sum-1)*index);

        int tranlateX= -(int) (Math.sin(radians)*radius);
        int tranlateY= -(int) (Math.cos(radians)*radius);

        ObjectAnimator anim1=ObjectAnimator.ofFloat(view,"translationX",tranlateX,0);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(view,"translationY",tranlateY,0);
        ObjectAnimator anim3=ObjectAnimator.ofFloat(view,"scaley",1,0.1f);
        ObjectAnimator anim4=ObjectAnimator.ofFloat(view,"scaleX",1,0.1f);
        ObjectAnimator anim5=ObjectAnimator.ofFloat(view,"alpha", 1,0);

        AnimatorSet set =new AnimatorSet();
        set.playTogether(anim1,anim2,anim3,anim4,anim5);
        set.setDuration(500);
        set.start();
    }

}
