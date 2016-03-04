package com.sdemo.recyleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2015/9/17.
 */
public class StaggeredGridViewAdapter extends RecyclerView.Adapter<StaggeredGridViewAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mDatas=new ArrayList<String>();
    private List<Integer> mHeight;

    public StaggeredGridViewAdapter(Context context, List<String> datas){
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
        this.mDatas=datas;

        mHeight=new ArrayList<Integer>();
        for(int i=0;i<mDatas.size();i++){

            mHeight.add((int) (100+Math.random()*300));

        }

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.item_tv,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int pos) {
       ViewGroup.LayoutParams lp=myViewHolder.itemView.getLayoutParams();
        lp.height=mHeight.get(pos);
        myViewHolder.itemView.setLayoutParams(lp);
        myViewHolder.tv.setText(mDatas.get(pos));



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MyViewHolder(View itemView) {

            super(itemView);

            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
