package com.ybb.testview.RecylerView.ChatUI;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ybb.testview.R;
import com.ybb.testview.View.CircleImageView;

import java.util.List;

/**
 * Created by ybb on 2018/3/20.
 */

public class MultipleItemAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>  {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.Accetp, R.layout.chat_a);
        addItemType(MultipleItem.SEND, R.layout.chat_b);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
         switch (helper.getItemViewType()){
             case MultipleItem.SEND:
                 CircleImageView bimgHead= helper.getView(R.id.ic_buser);
                 TextView tvSendptcontent= helper.getView(R.id.tv_sendptcontent);
                 Glide.with(mContext).load(item.getIcon()).into(bimgHead);
                 tvSendptcontent.setText(item.getContent());
                 break;
             case MultipleItem.Accetp:
                 CircleImageView imgHead= helper.getView(R.id.ic_user);
                 TextView tvAcceptContent= helper.getView(R.id.tv_acceptcontent);
                 Glide.with(mContext).load(item.getIcon()).into(imgHead);
                 tvAcceptContent.setText(item.getContent());
                 break;
         }
    }
}
