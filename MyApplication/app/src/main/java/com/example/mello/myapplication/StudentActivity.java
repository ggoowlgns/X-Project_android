package com.example.mello.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.mello.myapplication.Network.StuCheckAttendTask;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;



public class StudentActivity extends AppCompatActivity {

    TextView rl;
    TextView hak;
    TextView att;

    Map<String, String> params = new HashMap<>();

    String stu_attend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", 0);
        String name = sharedPreferences.getString("name", null);
        String id = sharedPreferences.getString("id", null);

        params.put("id", id);
        Log.i("이름",""+ name);
        Log.i("학번",""+ id);

        rl = (TextView)findViewById(R.id.etName);
        hak = (TextView)findViewById(R.id.etId);
        att = (TextView)findViewById(R.id.check_attend);

        rl.setText("이름: " + name);
        hak.setText("학번: " + id);
        new attThread().start();
    }

    private class attThread extends Thread{
        @Override
        public void run() {
            while(true){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StuCheckAttendTask stuCheckAttendTask = new StuCheckAttendTask(StudentActivity.this, params);
                        try {
                            stu_attend = stuCheckAttendTask.execute(params).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Log.i("StudentAct","stu_attend : "+stu_attend);
                        if (stu_attend.equals("1")) {
                            stu_attend = "출석되었습니다 !";
                        } else {
                            stu_attend = "결석입니다 !";
                        }
                        att.setText(stu_attend);
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

