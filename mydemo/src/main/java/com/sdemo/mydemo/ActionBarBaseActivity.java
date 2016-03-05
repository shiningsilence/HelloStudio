package com.sdemo.mydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.view.MenuItem;
import android.widget.TextView;



/**
 * ActionBar基础activity，进行统一的属性设置。<br>
 * 所有顶部带有actionbar的类都继承此类。
 *
 * @author 王植桦 ewangzhihua@yeah.net
 * @version 创建时间：2015年5月6日
 */
public class ActionBarBaseActivity extends Activity {
    protected Context ctx;
    protected String TAG;

    protected ActionBar actionBar;
    private TextView titleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = this;
    }

}
