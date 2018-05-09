package com.ybb.testview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybb.testview.RecylerView.ChatUI.ChatActivity;
import com.ybb.testview.View.FloatingView.FloatingViewActivity;
import com.ybb.testview.View.MenuView.MenuActivity;
import com.ybb.testview.View.PaintView.PaintViewActivity;
import com.ybb.testview.View.RuningTextView.RuningTextActivity;
import com.ybb.testview.View.WareView.WareViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    int color[]={
            R.color.red,
            R.color.colorSendName4,
            R.color.colorAccent,
            R.color.colorSendName6,
            R.color.colorSendName5
    };
        List<itemdata> data=new ArrayList<>();
          @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initData();
           recyclerView= (RecyclerView) findViewById(R.id.rv_simple);
           recyclerView.setLayoutManager(new GridLayoutManager(this,2));
           Adapter adapter=new Adapter(R.layout.item_main,data);
           recyclerView.setAdapter(adapter);
              adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                  @Override
                  public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                      itemdata item= (itemdata) adapter.getData().get(position);
                      startActivity(new Intent(MainActivity.this,item.getCls()));
                  }
              });

          }

    public void initData(){
        data.add(new itemdata("自定义view","展开菜单", MenuActivity.class));
        data.add(new itemdata("自定义view","画画板",PaintViewActivity.class));
        data.add(new itemdata("自定义view","水波纹",WareViewActivity.class));
        data.add(new itemdata("自定义view","悬浮球",FloatingViewActivity.class));
        data.add(new itemdata("RecyclerView","聊天界面",ChatActivity.class));
        data.add(new itemdata("自定义textview","跳动的数字",RuningTextActivity.class));


    }

    public class Adapter extends BaseQuickAdapter<itemdata,BaseViewHolder>{

        public Adapter(@LayoutRes int layoutResId, @Nullable List<itemdata> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, itemdata item) {
            TextView tvTag= helper.getView(R.id.tv_tag);
            tvTag.setText(item.getTag());

            TextView tvContent= helper.getView(R.id.content);
            tvContent.setText(item.getContent());

            int i = new Random().nextInt(5);
            RelativeLayout bg=helper.getView(R.id.rl_bg);
            bg.setBackgroundColor(getResources().getColor(color[i]));
            Log.i("ddddd",i+"");


        }
    }


    public class itemdata{
        public String tag;
        public String content;

        public Class<?> getCls() {
            return cls;
        }

        public void setCls(Class<?> cls) {
            this.cls = cls;
        }

        public  Class<?> cls;


        public itemdata(String tag,String content,Class<?> cls){
            this.tag=tag;
            this.content=content;
            this.cls=cls;
        }
        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }

}
