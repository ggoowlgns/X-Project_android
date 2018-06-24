package com.example.mello.myapplication;

import android.content.ContentUris;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mello.myapplication.Network.StuCheckAttendTask;
import com.example.mello.myapplication.Network.SubjectDetailTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.mello.myapplication.Util.Constants.isaAddr;
import static com.example.mello.myapplication.Util.Constants.isaFtpAddr;

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
                            stu_attend = "출석 되었습니다 !";
                        } else {
                            stu_attend = "결석 입니다 !";
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

