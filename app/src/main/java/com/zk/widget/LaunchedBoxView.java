package com.zk.widget;

import android.app.Activity;
import android.content.Context;
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

    private void init(Context context) {
        this.context = context;
        view = ((Activity) context).getLayoutInflater().inflate(
                R.layout.launched_box, null);
        initWidget();
        this.addView(view);
    }

    private void initWidget() {
        tv_message_1 = (TextView) view.findViewById(R.id.tv_message_1);
    }


    /*
      设置消息框状态信息
     */
    private void setStatusInfo( String msg ) {
        tv_message_1.setText("状态:" +msg);
    }
}
