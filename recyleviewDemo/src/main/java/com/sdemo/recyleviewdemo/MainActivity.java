package com.sdemo.recyleviewdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    private RecyclerView recyclerView;

    private List<String> mDatas;

    private MRecycleAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inintDatas();

        initView();
        mRecycleAdapter = new MRecycleAdapter(this, mDatas);
        recyclerView.setAdapter(mRecycleAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

       // recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.mrecycle);
    }

    private void inintDatas() {
        mDatas = new ArrayList<String>();

        for (int i = 'A'; i < 'Z'; i++) {

            mDatas.add((char) i + "");

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int id=  item.getItemId();
        switch(id){
            case R.id.action_gridview:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_listview:
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.action_hor_gridview:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                startActivity(new Intent(this,StaggeredGridViewActivity.class));
        }




        return super.onOptionsItemSelected(item);



    }
}
