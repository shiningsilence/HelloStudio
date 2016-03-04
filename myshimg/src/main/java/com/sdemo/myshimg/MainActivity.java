package com.sdemo.myshimg;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends Activity {
    private ImageView iv;
    private AnimationDrawable mAnimationDrawable;

    private MediaPlayer mp;
    private AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        am= (AudioManager) this.getSystemService(this.AUDIO_SERVICE);

        AssetManager mAssetManager=getAssets();
        try {
            AssetFileDescriptor afd= mAssetManager.openFd("wkqd.mp3");
            mp=new MediaPlayer();
            mp.setDataSource(afd.getFileDescriptor());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }






        iv= (ImageView) findViewById(R.id.iv);
        iv.setImageResource(R.drawable.animation);
        mAnimationDrawable= (AnimationDrawable) iv.getDrawable();
        mAnimationDrawable.setOneShot(false);
        mAnimationDrawable.start();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
