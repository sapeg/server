package com.pol.server;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.pol.server.Main.executor;
import static com.pol.server.Main.handler;

public class MainActivity extends Activity {
Button b;
    static public View t1,t2;
    static public MainActivity con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=this;
   //     Main m=new Main();

        handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                System.out.println("msg"+msg.what);

                if(msg.what==1)t1.setVisibility(View.VISIBLE);
                if(msg.what==2)t2.setVisibility(View.VISIBLE);
            }
        };

        b=(Button)findViewById(R.id.button);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        Intent intent = getIntent();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(executor!=null) {
                    try {
                        System.out.println("attempt to shutdown executor");
                        executor.shutdown();
                        executor.awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        System.err.println("tasks interrupted");
                    } finally {
                        if (!executor.isTerminated()) {
                            System.err.println("cancel non-finished tasks");
                        }
                        executor.shutdownNow();
                        System.out.println("shutdown finished");
                    }
                }

                finish();
               if( Main.server!=null){
                   try {
                       Main.server.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                try {
                    Main.main("asd");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

 /*       try {
            Main.main("asd");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
