package com.sdemo.myprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by dell on 2016/2/22.
 */
public class RoundProgressBarWithNumber extends HorizonProgressWithNumber {


    private int mRadius = dp2px(30);

    public RoundProgressBarWithNumber(Context context) {
        this(context, null);
    }

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        mReachedProgressBarHeight= (int) (mUnReachedProgressBarHeight*2.5f);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBarWithNumber);
        mRadius= (int) attributes.getDimension(R.styleable.RoundProgressBarWithNumber_radius,mRadius );
        attributes.recycle();
        mTextSize = sp2px(14);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setAntiAlias(true);
        mpaint.setDither(true);
        mpaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int paintWidth=Math.max(mReachedProgressBarHeight,mUnReachedProgressBarHeight);
        if(heightMode!=MeasureSpec.EXACTLY){
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom()
                    + mRadius * 2 + paintWidth);
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(exceptHeight,MeasureSpec.EXACTLY);
        }
        if(widthMode!=MeasureSpec.EXACTLY){
            int exceptWidth = (int) (getPaddingLeft() + getPaddingRight()
                    + mRadius * 2 + paintWidth);
            widthMeasureSpec=MeasureSpec.makeMeasureSpec(exceptWidth,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "%";
        float textWidth =mpaint.measureText(text);
        float textHeight = (mpaint.descent() + mpaint.ascent()) / 2;
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setColor(mUnReachedBarColor);
        mpaint.setStrokeWidth(mUnReachedProgressBarHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mpaint);


        mpaint.setColor(mReachedBarColor);
        mpaint.setStrokeWidth(mReachedProgressBarHeight);
        float sweepAngle=getProgress()*1.0f/getMax()*360;
        canvas.drawArc(new RectF(0,0,mRadius*2,mRadius*2),180,sweepAngle,false,mpaint);

        mpaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight,mpaint);
        canvas.restore();

       // super.onDraw(canvas);
    }

    private int dp2px(int defaultSizeTextOffset) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, defaultSizeTextOffset, getResources().getDisplayMetrics());
    }
    private int sp2px(int defaultTextSize) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,defaultTextSize,getResources().getDisplayMetrics());

    }
}
