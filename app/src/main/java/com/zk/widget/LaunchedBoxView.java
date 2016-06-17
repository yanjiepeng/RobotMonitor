package com.zk.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zk.robotmonitor.R;

/**
 * Created by Administrator on 2016/6/2. 自定义view显示Message
 */
public class LaunchedBoxView extends LinearLayout {

    Context context;

    public LaunchedBoxView(Context context) {
        super(context);
        if (!isInEditMode()) {
            //造成错误的代码段
            init(context);
        }
    }

    public LaunchedBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
//造成错误的代码段
            init(context);
        }
    }

    public LaunchedBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
//造成错误的代码段
            init(context);
        }
    }

    View view;
    TextView tv_message_1;
    TextView tv_box_title;

    private void init(Context context) {
        this.context = context;
        view = ((Activity) context).getLayoutInflater().inflate(
                R.layout.launched_box, null);

        initWidget();
        this.addView(view);
    }

    private void initWidget() {
        tv_message_1 = (TextView) view.findViewById(R.id.tv_message_1);
        tv_box_title = (TextView) view.findViewById(R.id.tv_box_titile);
    }


    /*
     设置消息框状态信息
    */
    public void setStatusInfo(String msg) {
        tv_message_1.setText(msg);
    }

    /*
     设置消息框状态信息
    */
    public void setStatusTextColor(int color) {

        tv_message_1.setTextColor(color);
    }

    /*
     设置消息框title背景
     */
    public void setStatusColor(int color) {
        tv_box_title.setBackgroundColor(color);
    }

    public void setTitle(CharSequence arg) {
        tv_box_title.setText(arg);
    }
}
