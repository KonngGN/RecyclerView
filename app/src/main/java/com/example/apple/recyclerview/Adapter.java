package com.example.apple.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * 作者：孔先生 on 2017/3/3 17:48
 * 邮箱：197726885@qq.com
 * 说明：
 */
public class Adapter extends CommonAdapter<String> {

    public Adapter(Context context, int layoutId, List<String> list) {
        super(context, layoutId, list);
    }

    @Override
    public void convert(ViewHolder holder, final String s, int position) {

        TextView textView = holder.getView(R.id.text);
        textView.setText(s);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
            }
        });
    }



}
