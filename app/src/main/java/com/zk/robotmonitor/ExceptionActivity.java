package com.zk.robotmonitor;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zk.adapter.TaskItemAdapter;
import com.zk.bean.taskbean;
import com.zk.database.DataService;

import java.util.ArrayList;
import java.util.List;

public class ExceptionActivity extends AppCompatActivity implements OnChartValueSelectedListener {


  private PieChart pieChart1 , pieChart2 ,pieChart3 ,pieChart4;
    String mParties[] = new String[]{"正常" ,"异常"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        initActionBar();
        initWidget();

        initContent(pieChart1, 99f, 1f, "切割机器人");
        initContent(pieChart2 , 90f, 10f ,"焊接机器人");
        initContent(pieChart3 , 99f, 1f ,"搬运机器人");
        initContent(pieChart4 , 99f, 1f ,"AGV搬运车");
    }

    private void initWidget() {

        pieChart1 = (PieChart) findViewById(R.id.piechart_1);
        pieChart2 = (PieChart) findViewById(R.id.piechart_2);
        pieChart3 = (PieChart) findViewById(R.id.piechart_3);
        pieChart4= (PieChart) findViewById(R.id.piechart_4);
    }

    /**
     * 初始化饼图数据
     * @param mChart  饼图对象
     * @param arg1 数据1
     * @param arg2 数据2
     */
    private void initContent(PieChart mChart , float arg1 , float arg2 ,String centerText ) {
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        mChart.setCenterText(generateCenterText(centerText));

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        //add a listener
        mChart.setOnChartValueSelectedListener(this);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        setData(mChart,arg1 ,arg2);
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setEnabled(false);

    }

    private void setData(PieChart mChart, float arg1, float arg2) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(arg1,0));
        yVals.add(new Entry(arg2,1));

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0 ; i < 2 ; i++ ) {
            xVals.add(mParties[i % mParties.length]);
        }
        PieDataSet dataSet = new PieDataSet(yVals , "passrate");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(Color.GREEN);
        colors.add(Color.RED);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);


        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        // dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        data.setValueTypeface(tf);
        mChart.setData(data);

        mChart.highlightValue(null);
        mChart.invalidate();
     }

    private SpannableString generateCenterText(String centerText) {

        SpannableString s = new SpannableString(centerText);
        s.setSpan(new RelativeSizeSpan(1.7f) , 0 ,centerText.length() , 0);
        return  s;
    }

    /*
        初始化action bar
     */
    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
            // 有小箭头，并且图标可以点击
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("机器人状态数据分析1");
        }

    }

    /*
        ActionBar的菜单监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
    }

    @Override
    public void onNothingSelected() {

    }
}
