package com.sdemo.demolist;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by dell on 2016/2/26.
 */
public class FaHuoTyleDialog extends Dialog {

    String[] contentArray={"设备", "矿产", "建材", "食品", "蔬菜", "生鲜", "药品", "化工", "木材", "家畜", "纺织品", "日用品", "电子电器",
            "农副产品", "其他类型"};
    GridView gv;
    SimpleAdapter simpleAdapter;


    public FaHuoTyleDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_fahuotype);
        setTitle("fahuotype");

        gv= (GridView) findViewById(R.id.gv);

        ArrayList<HashMap<String,Object>> al=new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<contentArray.length;i++){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("ItemText",contentArray[i]);
            al.add(map);
        }

        simpleAdapter=new SimpleAdapter(context,al,R.layout.item_grid_dialog_type,new String[]{"ItemText"},new int[]{R.id.tv_item_grid_type});
        gv.setAdapter(simpleAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onConfirm(contentArray[position]);

            }
        });






    }

    public void onConfirm(String s) {
        dismiss();
    }
}
