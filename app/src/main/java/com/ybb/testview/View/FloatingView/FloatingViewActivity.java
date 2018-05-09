package com.ybb.testview.View.FloatingView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ybb.testview.R;

/**
 * Created by ybb on 2018/3/16.
 *  首先要添加权限:  <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 *  API > 24 之后不能调用requestPermissions 来请求权限,必须要跳转到设置界面,让用户自己打开悬浮窗权限
 *
 *  Settings.canDrawOverlays方法判断授权结果
 *
 */

public class FloatingViewActivity extends AppCompatActivity {
    private Button btnShow,btnHide;
    private FloatingView floatingView;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);
        btnShow= (Button) findViewById(R.id.show);
        btnHide= (Button) findViewById(R.id.hide);

        floatingView= FloatingView.getInstance(getApplicationContext(),R.layout.floating);
        floatingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ddddddd","ddddddrrr");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfloatingView();
            }
        });
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingView.dismiss();
            }
        });
    }

    public void openfloatingView(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N && !Settings.canDrawOverlays(FloatingViewActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + FloatingViewActivity.this.getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            floatingView.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N && !Settings.canDrawOverlays(FloatingViewActivity.this)) {
                Toast.makeText(getApplicationContext(), "请在设置-权限设置里打开悬浮窗权限", Toast.LENGTH_SHORT).show();
            } else {
                floatingView.show();
            }
        }
    }
}
