package com.sdemo.mddemo;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnItemClickListener {

    ConvenientBanner convenientBanner;
    ListView listView;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    private ArrayAdapter transformerArrayAdapter;
    private ArrayList<String> transformerList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_layout);

        initView();
        initData();

    }

    private void initData() {

        initImageLoader();
        loadTestData();



        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            } //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        },localImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused}).setOnItemClickListener(this);


    }

    private void loadTestData() {
        for(int position=0;position<10;position++){
            localImages.add(getResId("ic_test_" + position,R.drawable.class));
            //各种翻页效果

            transformerArrayAdapter.notifyDataSetChanged();
        }
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions=new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_launcher).
                cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY-2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(configuration);


    }

    private void initView() {
         convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);

        DisplayMetrics dm=getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(dm.widthPixels,(int)(dm.widthPixels/1.6f));



        convenientBanner.setLayoutParams(layoutParams);


        listView= (ListView) findViewById(R.id.listView);
        transformerArrayAdapter=new ArrayAdapter(this,R.layout.adapter_transformer,transformerList);
        listView.setAdapter(transformerArrayAdapter);
        listView.setOnItemClickListener(this);







    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName,Class<?> c){
        try {
            Field field=c.getDeclaredField(variableName);
            return  field.getInt(field);
        } catch (Exception e) {
            e.printStackTrace();
            return  -1;
        }


    }

    @Override
    public void onItemClick(int position) {

    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }
}
