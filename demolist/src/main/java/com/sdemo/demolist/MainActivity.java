package com.sdemo.demolist;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button bt,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        final Context ctx=MainActivity.this;

        bt= (Button) findViewById(R.id.bt);
       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new FaHuoTyleDialog(ctx){
                   @Override
                   public void onConfirm(String s) {
                       Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();
                       super.onConfirm(s);
                   }
               }.show();
             //  new FaHuoTyleDialog(ctx).show();
              // Toast.makeText(ctx,"hello  man",Toast.LENGTH_LONG).show();
           }
       });


        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoadingDialog loadingDialog= new LoadingDialog(ctx);
                loadingDialog.title("hello");
                loadingDialog.message("message");
                loadingDialog.minShowTime(3000).show();


            }
        });

    }
}
