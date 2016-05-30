package com.zk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zk.bean.Taskbean;
import com.zk.robotmonitor.R;


import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 * 任务详情页列表适配
 */
public class TaskItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Taskbean> list;
    public TaskItemAdapter(Context context , List<Taskbean> list) {

        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if(convertView == null) {
            view = inflater.inflate(R.layout.task_list_item, null);
            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_work_zone = (TextView) view.findViewById(R.id.tv_work_zone);
            TextView tv_status = (TextView) view.findViewById(R.id.tv_status);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
            TextView tv_period = (TextView) view.findViewById(R.id.tv_period);

            //创建viewholder保存对象
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tv_id = tv_id;
            viewHolder.tv_type = tv_type;
            viewHolder.tv_name = tv_name;
            viewHolder.tv_work_zone = tv_work_zone;
            viewHolder.tv_status = tv_status;
            viewHolder.tv_time = tv_time;
            viewHolder.tv_period = tv_period;

            //保存viewholder
            view.setTag(viewHolder);

        } else {
            view = convertView;
            Log.v("TaskAdapter适配器", "复用");
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        //此处进行数据适配

        Taskbean tb = list.get(position % list.size());

        viewHolder.tv_id.setText(tb.getId());
        viewHolder.tv_name.setText(tb.getName());
        viewHolder.tv_work_zone.setText(tb.getWorkzone());
        viewHolder.tv_status.setText(tb.getStatus());
        if (tb.getStatus().equals("异常")) {
            view.setBackgroundColor(Color.RED);
        }
        viewHolder.tv_type.setText(tb.getType());
        viewHolder.tv_time.setText(tb.getTime());
        viewHolder.tv_period.setText(tb.getPeriod());


        return view;

    }

    class ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_work_zone;
        TextView tv_status;
        TextView tv_type;
        TextView tv_time;
        TextView tv_period;
    }
}
