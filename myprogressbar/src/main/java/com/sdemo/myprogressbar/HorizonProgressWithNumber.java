package com.sdemo.myprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dell on 2016/2/20.
 */
public class HorizonProgressWithNumber extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0xFFd3d6da;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 10;

    int mTextcolor=DEFAULT_TEXT_COLOR;
    int mTextSize=sp2px(DEFAULT_TEXT_SIZE);
    int mTextOffset=dp2px(DEFAULT_SIZE_TEXT_OFFSET);
    int mReachedBarColor=DEFAULT_TEXT_COLOR;
    int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;
    int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);
    int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);
    int mRealWidth;
    boolean mIfDrawText = true;
    int VISIBLE = 0;

    Drawable img;




    Paint mpaint=new Paint();

    private int dp2px(int defaultSizeTextOffset) {
     return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,defaultSizeTextOffset,getResources().getDisplayMetrics());
    }

    private int sp2px(int defaultTextSize) {
       return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,defaultTextSize,getResources().getDisplayMetrics());

    }

    public HorizonProgressWithNumber(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }
    public HorizonProgressWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(true);
        obtainStyledAttributeSets(attrs);
        mpaint.setTextSize(mTextSize);
        mpaint.setColor(mTextcolor);
    }

    /**
     * get styled attributes
     * @param attrs
     */
    private void obtainStyledAttributeSets(AttributeSet attrs) {
        TypedArray attributes=getContext().obtainStyledAttributes(attrs,R.styleable.HorizontalProgressBarWithNymber);
        mTextcolor=attributes.getColor(R.styleable.HorizontalProgressBarWithNymber_progress_text_color,DEFAULT_TEXT_COLOR);
        mTextSize= (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNymber_progress_text_size,mTextSize);
        mReachedBarColor=attributes.getColor(R.styleable.HorizontalProgressBarWithNymber_progress_reached_color,DEFAULT_TEXT_COLOR);
        mUnReachedBarColor=attributes.getColor(R.styleable.HorizontalProgressBarWithNymber_progress_unreached_color,DEFAULT_COLOR_UNREACHED_COLOR);
        mUnReachedProgressBarHeight= (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNymber_progress_unreached_height,mUnReachedProgressBarHeight);
        mReachedProgressBarHeight= (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNymber_progress_reached_height,mReachedProgressBarHeight);
       mTextOffset= (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNymber_progress_text_offset,mTextOffset);
        //add
        // img=attributes.getDrawable(R.styleable.HorizontalProgressBarWithNymber_progress_img);


        int textVisible= attributes.getInt(R.styleable.HorizontalProgressBarWithNymber_progress_text_visible,VISIBLE);
        if(textVisible!=VISIBLE){
            mIfDrawText=false;
        }
       // bg=((BitmapDrawable)attributes.getDrawable(R.styleable.HorizontalProgressBarWithNymber_progress_img)).getBitmap();
        attributes.recycle();



    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        if(heightMode!=MeasureSpec.EXACTLY){
            float textHeight=(mpaint.descent()+mpaint.ascent());
            int exceptHeight= (int) (getPaddingTop()+getPaddingBottom()+Math.max((Math.max(mReachedProgressBarHeight,mUnReachedProgressBarHeight)),Math.abs(textHeight)));
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(exceptHeight,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        //画笔平移到指定paddingLeft， getHeight() / 2位置，注意以后坐标都为以此为0，0
        canvas.translate(getPaddingLeft(),getHeight()/2);
        boolean noNeedBg = false;
        //当前进度和总值的比例
        float radio=getProgress()*1.0f/getMax();
        //已到达的宽度
        float progressPosX=mRealWidth*radio;
        //绘制的文本
        String text = getProgress() + "%";
        //拿到字体的宽度和高度
        float textWidth = mpaint.measureText(text);
        float textHeight = (mpaint.descent() + mpaint.ascent()) / 2;
        //如果到达最后，则未到达的进度条不需要绘制
        if(textWidth+progressPosX>mRealWidth){
            progressPosX=mRealWidth-textWidth;
            noNeedBg=true;
        }
        // 绘制已到达的进度
        float endX = progressPosX - mTextOffset / 2;
        if(endX>0){
            mpaint.setColor(mReachedBarColor);
            mpaint.setStrokeWidth(mReachedProgressBarHeight);
            canvas.drawLine(0,0,endX,0,mpaint);
        }
        // 绘制文本
        if(mIfDrawText){
            mpaint.setColor(mTextcolor);
           canvas.drawText(text, progressPosX, -textHeight, mpaint);

            //add
           /* BitmapDrawable bitmapDrawable= (BitmapDrawable) img;
            BitmapDrawable bitmapDrawable1= (BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher);
            canvas.drawBitmap(bitmapDrawable1.getBitmap(),progressPosX,-textHeight,mpaint);*/
        }
        //绘制图片


              // canvas.drawBitmap(bg,progressPosX,-textHeight,mpaint);

        // 绘制未到达的进度条
        if(!noNeedBg){
            float start=progressPosX+textWidth+mTextOffset/2;
            mpaint.setColor(mUnReachedBarColor);
            mpaint.setStrokeWidth(mUnReachedProgressBarHeight);
            canvas.drawLine(start,0,mRealWidth,0,mpaint);
        }
        canvas.restore();

        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth=w-getPaddingLeft()-getPaddingRight();

    }
}
