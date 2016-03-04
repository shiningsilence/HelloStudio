package com.sdemo.tabdemo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager vp;
    // private FrameLayout fl;
    private LinearLayout mTabweixin;
    private LinearLayout mTabfriend;
    private LinearLayout mTabaddress;
    private LinearLayout mTabsetting;

    private ImageButton mIBweixin;
    private ImageButton mIBfriend;
    private ImageButton mIBaddress;
    private ImageButton mIBsetting;

    private Fragment mWeixinFragment;
    private Fragment mFriendFragment;
    private Fragment mAddressFragment;
    private Fragment mSetFragment;


    //private List<View> mViewList = new ArrayList<>();
    private List<Fragment> mFragmentList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        initView();



       /* vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViewList.get(position);
                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                // super.destroyItem(container, position, object);
                container.removeView(mViewList.get(position));
            }
        });*/

        initEvents();
        selectFragmen(0);


    }

    private void initEvents() {
        mTabweixin.setOnClickListener(this);
        mTabfriend.setOnClickListener(this);
        mTabaddress.setOnClickListener(this);
        mTabsetting.setOnClickListener(this);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectFragmen(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /* vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              //  int currentItem=vp.getCurrentItem();
                resetImg();
                switch (position){
                    case 0:
                        mIBweixin.setImageResource(R.mipmap.tab_weixin_pressed);
                        break;
                    case 1:
                        mIBfriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                        break;
                    case 2:
                        mIBaddress.setImageResource(R.mipmap.tab_address_pressed);
                        break;
                    case  3:
                        mIBsetting.setImageResource(R.mipmap.tab_settings_pressed);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

    }

    private void initView() {
         vp= (ViewPager) findViewById(R.id.vp);
        //fl = (FrameLayout) findViewById(R.id.fl);
        mTabweixin = (LinearLayout) findViewById(R.id.ll_tab_weixin);
        mTabfriend = (LinearLayout) findViewById(R.id.ll_tab_find_frd_normal);
        mTabaddress = (LinearLayout) findViewById(R.id.ll_tab_address_normal);
        mTabsetting = (LinearLayout) findViewById(R.id.ll_tab_settings_normal);
        mIBweixin = (ImageButton) findViewById(R.id.ll_tab_weixin_img);
        mIBfriend = (ImageButton) findViewById(R.id.ll_tab_find_frd_normal_img);
        mIBaddress = (ImageButton) findViewById(R.id.ll_tab_address_normal_img);
        mIBsetting = (ImageButton) findViewById(R.id.ll_tab_settings_normal_img);

        LayoutInflater inflater = LayoutInflater.from(this);
      //  mViewList.add(inflater.inflate(R.layout.tab1, null));
       // mViewList.add(inflater.inflate(R.layout.tab2, null));
      //  mViewList.add(inflater.inflate(R.layout.tab3, null));
      //  mViewList.add(inflater.inflate(R.layout.tab4, null));

        mWeixinFragment=new WeixinFragment();
        mFriendFragment=new FriendFragment();
        mAddressFragment=new AddressFragment();
        mSetFragment=new SetFragment();

        mFragmentList.add(mWeixinFragment);
        mFragmentList.add(mFriendFragment);
        mFragmentList.add(mAddressFragment);
        mFragmentList.add(mSetFragment);

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });



    }


    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.ll_tab_weixin:
                // mIBweixin.setImageResource(R.mipmap.tab_weixin_pressed);
                // vp.setCurrentItem(0);
                selectFragmen(0);
                break;
            case R.id.ll_tab_find_frd_normal:
                // mIBfriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                // vp.setCurrentItem(1);
                selectFragmen(1);
                break;
            case R.id.ll_tab_address_normal:

                // mIBaddress.setImageResource(R.mipmap.tab_address_pressed);
                // vp.setCurrentItem(2);
                selectFragmen(2);
                break;
            case R.id.ll_tab_settings_normal:
                //mIBsetting.setImageResource(R.mipmap.tab_settings_pressed);
                //  vp.setCurrentItem(3);
                selectFragmen(3);
                break;
            default:
                break;

        }

    }
    private void selectFragmen(int i) {
        resetImg();
        selectTab(i);
        vp.setCurrentItem(i);



    }

    private void selectTab(int i){
        switch (i) {
            case 0:

                mIBweixin.setImageResource(R.mipmap.tab_weixin_pressed);
                break;
            case 1:

                mIBfriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                break;
            case 2:

                mIBaddress.setImageResource(R.mipmap.tab_address_pressed);
                break;
            case 3:

                mIBsetting.setImageResource(R.mipmap.tab_settings_pressed);
                break;



        }

    }


   /* private void selectFragmen(int i) {
        resetImg();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        hideAllFragment(tr);
        switch (i) {
            case 0:
                if (mWeixinFragment == null) {
                    mWeixinFragment = new WeixinFragment();
                    tr.add(R.id.fl, mWeixinFragment);
                } else {
                    tr.show(mWeixinFragment);
                }
                mIBweixin.setImageResource(R.mipmap.tab_weixin_pressed);
                break;
            case 1:
                if(mFriendFragment==null){
                    mFriendFragment=new FriendFragment();
                    tr.add(R.id.fl,mFriendFragment);
                }else{
                    tr.show(mFriendFragment);
                }
                mIBfriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                break;
            case 2:
                if(mAddressFragment==null){
                    mAddressFragment=new AddressFragment();
                    tr.add(R.id.fl,mAddressFragment);
                }else{
                    tr.show(mAddressFragment);
                }
                mIBaddress.setImageResource(R.mipmap.tab_address_pressed);
                break;
            case 3:
                if(mSetFragment==null){
                    mSetFragment=new SetFragment();
                    tr.add(R.id.fl,mSetFragment);
                }else{
                    tr.show(mSetFragment);
                }
                mIBsetting.setImageResource(R.mipmap.tab_settings_pressed);
                break;



        }
        tr.commit();
    }*/

    private void hideAllFragment(FragmentTransaction tr) {
        if (mWeixinFragment != null) {
            tr.hide(mWeixinFragment);
        } if (mFriendFragment != null) {
            tr.hide(mFriendFragment);
        }  if (mAddressFragment != null) {
            tr.hide(mAddressFragment);
        }  if (mSetFragment != null) {
            tr.hide(mSetFragment);
        }
    }

    private void resetImg() {
        mIBweixin.setImageResource(R.mipmap.tab_weixin_normal);
        mIBfriend.setImageResource(R.mipmap.tab_find_frd_normal);
        mIBaddress.setImageResource(R.mipmap.tab_address_normal);
        mIBsetting.setImageResource(R.mipmap.tab_settings_normal);
    }
}
