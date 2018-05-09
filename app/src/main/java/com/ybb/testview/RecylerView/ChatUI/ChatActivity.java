package com.ybb.testview.RecylerView.ChatUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ybb.testview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybb on 2018/3/20.
 * 聊天界面
 * 参考链接:https://www.jianshu.com/p/b343fcff51b0 , https://www.jianshu.com/p/cf29d4e45536
 */

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MultipleItemAdapter adapter;
    private List<MultipleItem> data=new ArrayList<>();
    private TextView tvSend;
    private EditText edContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        for (int i=0;i<10;i++){
            if (i%2==1){
                data.add(new MultipleItem(MultipleItem.Accetp,"http://img1.touxiang.cn/uploads/20130411/11-070018_562.jpg","I LOVE YOU"));
            }else {
                data.add(new MultipleItem(MultipleItem.SEND,"http://img.duoziwang.com/2016/09/19/21305333828.png","ME TOO"));
            }

        }
        recyclerView= (RecyclerView) findViewById(R.id.rv_chat);
        adapter=new MultipleItemAdapter(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        edContent= (EditText) findViewById(R.id.et);
        tvSend= (TextView) findViewById(R.id.tvSend);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=  edContent.getText().toString();
                adapter.addData(adapter.getItemCount(),new MultipleItem(MultipleItem.SEND,"http://img.duoziwang.com/2016/09/19/21305333828.png",content));
                recyclerView.smoothScrollToPosition(adapter.getItemCount()); //recyclerview 滚动到底部
                edContent.setText(null);

            }
        });
    }
}
