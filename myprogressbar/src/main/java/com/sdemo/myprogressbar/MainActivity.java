package com.sdemo.myprogressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    HorizonProgressWithNumber mProgressBar;
    RoundProgressBarWithNumber mRoundProgressBar;
    private static final int MSG_PROGRESS_UPDATE = 0x110;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int progress=mProgressBar.getProgress();
            mProgressBar.setProgress(++progress);


            int mRprogress=mRoundProgressBar.getProgress();
            mRoundProgressBar.setProgress(++mRprogress);
            if(progress>100){
                handler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            handler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE,100);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar= (HorizonProgressWithNumber) findViewById(R.id.id_progressbar01);
        mRoundProgressBar= (RoundProgressBarWithNumber) findViewById(R.id.myRoundProgressBar);
        handler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
    }
}
