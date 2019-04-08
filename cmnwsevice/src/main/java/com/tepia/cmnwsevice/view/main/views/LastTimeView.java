package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tepia.cmnwsevice.R;

//import androidx.annotation.Nullable;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class LastTimeView extends View {

    private static final String TAG = LastTimeView.class.getSimpleName();

    private Context ctx;

    //绘制圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;

    //绘制背景圆弧
    private Paint mBgArcPaint;
    private int mBgArcColor;
    private float mBgArcWidth;

    public LastTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ctx = context;

        initAttrs(attrs);
        initPaint();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.LastTimeView);

        mArcWidth = typedArray.getDimension(R.styleable.LastTimeView_arcWidth, 5);
        mStartAngle = typedArray.getFloat(R.styleable.LastTimeView_startAngle, 0);
        mSweepAngle = typedArray.getFloat(R.styleable.LastTimeView_sweepAngle, 180);


        mBgArcColor = typedArray.getColor(R.styleable.LastTimeView_bgArcColor, Color.WHITE);
        mBgArcWidth = typedArray.getDimension(R.styleable.LastTimeView_bgArcWidth, 10);

        typedArray.recycle();
    }

    private void initPaint() {
        mArcPaint = new Paint();
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgArcPaint = new Paint();
        mBgArcPaint.setColor(mBgArcColor);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(MiscUtil.measure(widthMeasureSpec, mDefaultSize),
//                MiscUtil.measure(heightMeasureSpec, mDefaultSize));
    }
}
