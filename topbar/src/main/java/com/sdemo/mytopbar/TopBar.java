package com.sdemo.mytopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TopBar extends RelativeLayout {
    private Button leftButton,rightButton;
    private TextView topBartv;

    private String tvtitle;
    private float  titleTextSize;
    private int    titleTextColor;

    private String   leftText;
    private int      leftTextColor;
    private Drawable leftTextBackground;

    private String   rightText;
    private int       rightTextColor;
    private Drawable  rightTextBackground;


    private LayoutParams  leftParams,rightParams,tvtilteParams;

    private OnTopBarClickListener listener;

   public interface  OnTopBarClickListener{
        void setLeftOnClick();
        void setRightOnClick();


    };

    public void setOnTopBarClickListener(OnTopBarClickListener listener){
        this.listener=listener;

    }

    public void setLeftButtonIsVisable(boolean flag){
        if(flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }
    }





    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TopBar);

        tvtitle=ta.getString(R.styleable.TopBar_tvtitle);
        titleTextSize=ta.getFloat(R.styleable.TopBar_titleTextSize, 0);
        titleTextColor=ta.getInt(R.styleable.TopBar_titleTextColor, 0);

        leftText=ta.getString(R.styleable.TopBar_leftText);
        leftTextColor=ta.getInt(R.styleable.TopBar_leftTextColor, 0);
        leftTextBackground=ta.getDrawable(R.styleable.TopBar_leftTextBackground);


        rightText=ta.getString(R.styleable.TopBar_rightText);
        rightTextColor=ta.getInt(R.styleable.TopBar_rightTextColor, 0);
        rightTextBackground=ta.getDrawable(R.styleable.TopBar_rightTextBackground);

        ta.recycle();

        leftButton=new Button(context);
        rightButton=new Button(context);
        topBartv=new TextView(context);


        topBartv.setText(tvtitle);
        topBartv.setTextColor(titleTextColor);
        topBartv.setTextSize(titleTextSize);
        topBartv.setGravity(Gravity.CENTER);

        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftTextBackground);

        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightTextBackground);

        setBackgroundColor(0xFFF59563);


        leftParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);

        addView(leftButton, leftParams);

        rightParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(rightButton, rightParams);

        tvtilteParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        tvtilteParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(topBartv, tvtilteParams);






        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setLeftOnClick();

            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setRightOnClick();

            }
        });




    }
}
