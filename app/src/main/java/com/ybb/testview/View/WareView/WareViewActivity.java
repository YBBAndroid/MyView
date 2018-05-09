package com.ybb.testview.View.WareView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ybb.testview.R;

/**
 * Created by ybb on 2018/3/16.
 */

public class WareViewActivity extends AppCompatActivity {
    private Button btnStart,btnStop;
    private WaveView waveView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware);
        waveView= (WaveView) findViewById(R.id.waveView);
        btnStart= (Button) findViewById(R.id.btn_start);
        btnStop= (Button) findViewById(R.id.btn_top);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waveView.startAnim();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
