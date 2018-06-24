package com.example.mello.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mello.myapplication.Network.HttpClient;
import com.example.mello.myapplication.Network.SubjectDetailTask;
import com.example.mello.myapplication.Util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CheckingActivity extends AppCompatActivity {
    String num;
    private String [] check = {};
    ArrayList<String> checkList = new ArrayList<String>(Arrays.asList(check));
    ArrayAdapter<String> adapter;
    Map<String, String> params = new HashMap<>();
    ListView list;
    TextView show;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        Intent intent = getIntent();
        String sub_name = intent.getExtras().getString("sub_name");
        params.put("sub_name", sub_name);
        Log.i("교과",""+sub_name);
        show = (TextView)findViewById(R.id.show);
        show.setText(sub_name);
        list = (ListView) findViewById(R.id.num);

        new listthread().start();


    }


    private class listthread extends Thread{
        @Override
        public void run() {
                while(true){
                    adapter = new ArrayAdapter<String>(CheckingActivity.this,android.R.layout.simple_list_item_1,checkList);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(adapter);//
                        checkList = new ArrayList<String>(Arrays.asList(check));//list 초기화
                        SubjectDetailTask subjectDetailTask = new SubjectDetailTask(CheckingActivity.this, params);
                        String sub_detail_res = "EMPTY";

                        try {
                            sub_detail_res = subjectDetailTask.execute(params).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        Log.i("CHeignAct", "" + sub_detail_res);

                        if (!sub_detail_res.equals("EMPTY")) {
                            String[] stu_arry = sub_detail_res.split("/");
                            for (String stu : stu_arry) {
                                Log.i("CHeichasd ", "stu : " + stu);
                                String id = stu.split(":")[0];
                                String attend = stu.split(":")[1];
                                if (attend.equals("1")) {
                                    attend = "출석";
                                } else {
                                    attend = "결석";
                                }
                                checkList.add(0, "학번 : " + id + "         출석확인 : " + attend);
                            }
                        }

                    }
                });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }


        }
    }




}
