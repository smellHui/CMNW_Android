package com.tepia.common_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/9
 * Time            :       14:38
 * Version         :       1.0
 * 功能描述        :        弧形进度条
 **/
public class ColorArcProgressBar extends View {

    private int mWidth;
    private int mHeight;
    private int diameter = 5000; //直径
    private float centerX; //圆心X坐标
    private float centerY; //圆心Y坐标

    private Paint allArcPaint;
    private Paint progressPaint;
    private Paint progressPaint2;
    private Paint vTextPaint;
    private Paint unitPaint;
    private Paint degreePaint;
    private Paint curSpeedPaint;

    private RectF bgRect;
    private RectF bgRect2;

    private ValueAnimator progressAnimator;
    private PaintFlagsDrawFilter mDrawFilter;
    private SweepGradient sweepGradient;
    private Matrix rotateMatrix;

    private float startAngle = 135;
    private float sweepAngle = 270;
    private float currentAngle = 0;
    private float lastAngle;
    private int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};
    private float maxValues = 100;
    private float curValues = 0;
    /**
     * 背景圆环的宽度
     */
    private float bgArcWidth = dipToPx(3);
    /**
     * 两个背景圆环的间隔
     */
    private float bgArcJGWidth = dipToPx(15);
    private float progressWidth = dipToPx(3);
    private float textSize = dipToPx(60);
    private float unitSize = dipToPx(18);
    private float curSpeedSize = dipToPx(18);
    private int aniSpeed = 1000;
    private float longdegree = dipToPx(13);
    private float shortdegree = dipToPx(5);
    private final int DEGREE_PROGRESS_DISTANCE = dipToPx(8);

    private int unitColor = 0xffffffff;
    private int contentColor = 0xffffffff;
    private int longDegreeColor = 0xffffffff;
    private int shortDegreeColor = 0xffffffff;
    /**
     * 圆圈背景色（画笔颜色）
     */
    private int bgArcColor = 0xffD6F7EB;
    /**
     * 进度调颜色
     */
    private int progressColor = 0xffffffff;
    /**
     * 标题
     */
    private String titleString;
    /**
     * 单位
     */
    private String unitString;

    private boolean isNeedTitle;
    private boolean isNeedUnit;
    private boolean isNeedDial;
    private boolean isNeedContent;

    // sweepAngle / maxValues 的值
    private float k;
    /**
     * 进度球的半径
     */
    private float thumbRadis = dipToPx(4.5f);
    /**
     * 整个宽度在屏幕的权重
     */
    private float widthWeight;


    public ColorArcProgressBar(Context context) {
        super(context, null);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initCofig(context, attrs);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCofig(context, attrs);
        initView();
    }

    /**
     * 初始化布局配置
     *
     * @param context
     * @param attrs
     */
    private void initCofig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorArcProgressBar);
        int color1 = a.getColor(R.styleable.ColorArcProgressBar_front_color1, Color.GREEN);
        int color2 = a.getColor(R.styleable.ColorArcProgressBar_front_color2, color1);
        int color3 = a.getColor(R.styleable.ColorArcProgressBar_front_color3, color1);
        colors = new int[]{color1, color2, color3, color3};

        sweepAngle = a.getInteger(R.styleable.ColorArcProgressBar_total_engle, 270);
        widthWeight = a.getFloat(R.styleable.ColorArcProgressBar_width_weight, 0.66f);
        bgArcWidth = a.getDimension(R.styleable.ColorArcProgressBar_back_width, dipToPx(3));
        bgArcJGWidth = a.getDimension(R.styleable.ColorArcProgressBar_bgArcJGWidth, dipToPx(10));
        bgArcColor = a.getColor(R.styleable.ColorArcProgressBar_back_color, Color.GREEN);
        progressWidth = a.getDimension(R.styleable.ColorArcProgressBar_progress_width, dipToPx(3));
        progressColor = a.getColor(R.styleable.ColorArcProgressBar_progress_color, Color.RED);
        isNeedTitle = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_title, false);
        isNeedContent = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_content, false);
        isNeedUnit = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_unit, false);
        isNeedDial = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_dial, false);
        unitString = a.getString(R.styleable.ColorArcProgressBar_string_unit);
        unitSize = a.getDimension(R.styleable.ColorArcProgressBar_string_unit_textsize, dipToPx(18));
        curSpeedSize = a.getDimension(R.styleable.ColorArcProgressBar_string_title_textsize, dipToPx(18));
        textSize = a.getDimension(R.styleable.ColorArcProgressBar_string_content_textsize, dipToPx(60));
        thumbRadis = a.getDimension(R.styleable.ColorArcProgressBar_thumb_radis, dipToPx(4.5f));
        titleString = a.getString(R.styleable.ColorArcProgressBar_string_title);
        curValues = a.getFloat(R.styleable.ColorArcProgressBar_current_value, 0);
        maxValues = a.getFloat(R.styleable.ColorArcProgressBar_max_value, 60);
        setCurrentValues(curValues);
        setMaxValues(maxValues);
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE);
        int height = (int) (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE);
        setMeasuredDimension(width, height);
    }

    private void initView() {

        diameter = (int) (widthWeight * getScreenWidth());
        //弧形的矩阵区域
        bgRect = new RectF();
        bgRect.top = longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE;
        bgRect.left = longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE;
        bgRect.right = diameter + (longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE);
        bgRect.bottom = diameter + (longdegree + progressWidth / 2 + DEGREE_PROGRESS_DISTANCE);
        //虚线弧形的矩阵区域
        bgRect2 = new RectF();
        bgRect2.top = bgRect.top + bgArcJGWidth;
        bgRect2.left = bgRect.left + bgArcJGWidth;
        bgRect2.right = bgRect.right - bgArcJGWidth;
        bgRect2.bottom = bgRect.bottom - bgArcJGWidth;

        //圆心
        centerX = (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;
        centerY = (2 * longdegree + progressWidth + diameter + 2 * DEGREE_PROGRESS_DISTANCE) / 2;

        //外部刻度线
        degreePaint = new Paint();
        degreePaint.setColor(longDegreeColor);

        //整个弧形
        allArcPaint = new Paint();
        allArcPaint.setAntiAlias(true);
        allArcPaint.setStyle(Paint.Style.STROKE);
        allArcPaint.setStrokeWidth(bgArcWidth);
        allArcPaint.setColor(bgArcColor);
        allArcPaint.setStrokeCap(Paint.Cap.ROUND);

        //当前进度的弧形
        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setColor(progressColor);

        progressPaint2 = new Paint();
        progressPaint2.setAntiAlias(true);
        progressPaint2.setStyle(Paint.Style.FILL);
        progressPaint2.setStrokeCap(Paint.Cap.ROUND);
        progressPaint2.setStrokeWidth(progressWidth);
        progressPaint2.setColor(progressColor);

        //内容显示文字
        vTextPaint = new Paint();
        vTextPaint.setTextSize(textSize);
        vTextPaint.setColor(contentColor);
        vTextPaint.setTextAlign(Paint.Align.CENTER);

        //显示单位文字
        unitPaint = new Paint();
        unitPaint.setTextSize(unitSize);
        unitPaint.setColor(unitColor);
        unitPaint.setTextAlign(Paint.Align.CENTER);

        //显示标题文字
        curSpeedPaint = new Paint();
        curSpeedPaint.setTextSize(curSpeedSize);
        curSpeedPaint.setColor(unitColor);
        curSpeedPaint.setTextAlign(Paint.Align.CENTER);

        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        sweepGradient = new SweepGradient(centerX, centerY, colors, null);
        rotateMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //抗锯齿
        canvas.setDrawFilter(mDrawFilter);

        if (isNeedDial) {
            //画刻度线
            for (int i = 0; i < 40; i++) {
                if (i > 15 && i < 25) {
                    canvas.rotate(9, centerX, centerY);
                    continue;
                }
                if (i % 5 == 0) {
                    degreePaint.setStrokeWidth(dipToPx(2));
                    degreePaint.setColor(longDegreeColor);
                    canvas.drawLine(centerX, centerY - diameter / 2 - progressWidth / 2 - DEGREE_PROGRESS_DISTANCE,
                            centerX, centerY - diameter / 2 - progressWidth / 2 - DEGREE_PROGRESS_DISTANCE - longdegree, degreePaint);
                } else {
                    degreePaint.setStrokeWidth(dipToPx(1.4f));
                    degreePaint.setColor(shortDegreeColor);
                    canvas.drawLine(centerX, centerY - diameter / 2 - progressWidth / 2 - DEGREE_PROGRESS_DISTANCE - (longdegree - shortdegree) / 2,
                            centerX, centerY - diameter / 2 - progressWidth / 2 - DEGREE_PROGRESS_DISTANCE - (longdegree - shortdegree) / 2 - shortdegree, degreePaint);
                }

                canvas.rotate(9, centerX, centerY);
            }
        }

        //整个弧  背景
        canvas.drawArc(bgRect, startAngle, sweepAngle, false, allArcPaint);
        for (int i = 0; i <= maxValues; i++) {
            if (i % 6 == 0 && i + 4 <= maxValues) {
                canvas.drawArc(bgRect2, startAngle + i * k, 4 * k, false, allArcPaint);
            }
        }

        //设置渐变色
        rotateMatrix.setRotate(130, centerX, centerY);
        sweepGradient.setLocalMatrix(rotateMatrix);
//        progressPaint.setShader(sweepGradient);

        //当前进度
        canvas.drawArc(bgRect, startAngle, currentAngle, false, progressPaint);
        canvas.drawCircle((float) (centerX + diameter / 2 * Math.cos((360 - startAngle - currentAngle) * Math.PI / 180)),
                (float) (centerY - diameter / 2 * Math.sin((360 - startAngle - currentAngle) * Math.PI / 180)), thumbRadis, progressPaint2);
        if (isNeedContent) {
            canvas.drawText(String.format("%.0f", curValues), centerX - String.format("%.0f", curValues).length() * textSize / 4, centerY, vTextPaint);
        }
        if (isNeedUnit) {
            canvas.drawText(unitString, centerX + String.format("%.0f", curValues).length() * unitSize, centerY, unitPaint);
        }
        if (isNeedTitle) {
            canvas.drawText(titleString, centerX, centerY + 1 * textSize / 2, curSpeedPaint);
        }

        invalidate();

    }

    /**
     * 设置最大值
     *
     * @param maxValues
     */
    public void setMaxValues(float maxValues) {
        this.maxValues = maxValues;
        k = sweepAngle / maxValues;
    }

    /**
     * 设置当前值
     *
     * @param currentValues
     */
    public void setCurrentValues(float currentValues) {
        if (currentValues > maxValues) {
            currentValues = maxValues;
        }
        if (currentValues < 0) {
            currentValues = 0;
        }
        this.curValues = currentValues;
        lastAngle = currentAngle;
        setAnimation(lastAngle, currentValues * k, aniSpeed);
    }

    /**
     * 设置整个圆弧宽度
     *
     * @param bgArcWidth
     */
    public void setBgArcWidth(int bgArcWidth) {
        this.bgArcWidth = bgArcWidth;
    }

    /**
     * 设置进度宽度
     *
     * @param progressWidth
     */
    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
    }

    /**
     * 设置速度文字大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * 设置单位文字大小
     *
     * @param unitSize
     */
    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    /**
     * 设置单位文字
     *
     * @param hintString
     */
    public void setUnit(String hintString) {
        this.unitString = hintString;
        invalidate();
    }

    /**
     * 设置直径大小
     *
     * @param diameter
     */
    public void setDiameter(int diameter) {
        this.diameter = dipToPx(diameter);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    private void setTitle(String title) {
        this.titleString = title;
    }

    /**
     * 设置是否显示标题
     *
     * @param isNeedTitle
     */
    private void setIsNeedTitle(boolean isNeedTitle) {
        this.isNeedTitle = isNeedTitle;
    }

    /**
     * 设置是否显示单位文字
     *
     * @param isNeedUnit
     */
    private void setIsNeedUnit(boolean isNeedUnit) {
        this.isNeedUnit = isNeedUnit;
    }

    /**
     * 设置是否显示外部刻度盘
     *
     * @param isNeedDial
     */
    private void setIsNeedDial(boolean isNeedDial) {
        this.isNeedDial = isNeedDial;
    }

    /**
     * 为进度设置动画
     *
     * @param last
     * @param current
     */
    private void setAnimation(float last, float current, int length) {
        progressAnimator = ValueAnimator.ofFloat(last, current);
        progressAnimator.setDuration(length);
        progressAnimator.setTarget(currentAngle);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (Float) animation.getAnimatedValue();
                curValues = currentAngle / k;
            }
        });
        progressAnimator.start();
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
