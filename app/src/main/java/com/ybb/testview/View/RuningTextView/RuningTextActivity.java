package com.ybb.testview.View.RuningTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ybb.testview.R;

/**
 * Created by ybb on 2018/4/3.
 */

public class RuningTextActivity extends Activity {
    private NumberRunningTextView numbertext;
    private NumberRunningTextView numbertext2;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runingtext);
        numbertext = (NumberRunningTextView) findViewById(R.id.numbertext);

        numbertext2 = (NumberRunningTextView) findViewById(R.id.numbertext2);


        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbertext.setContent("146513.49");
                numbertext2.setContent("146513");
            }
        });
    }

}
