package com.example.mello.myapplication;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mello.myapplication.Network.LoginTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText idEnter, pwEnter;
    String id, pw;
    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEnter = (EditText)findViewById(R.id.etEmail);
        pwEnter = (EditText)findViewById(R.id.etPassword);
        Button button = (Button) findViewById(R.id.btn_login);
        Button button_join = (Button)findViewById(R.id.btnRegist);


        button.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               if (checkValid()) {
                   long currentClickTime = SystemClock.uptimeMillis();
                   long elapsedTime = currentClickTime - mLastClickTime;
                   mLastClickTime = currentClickTime;
                   // 중복 클릭인 경우
                   if (elapsedTime <= MIN_CLICK_INTERVAL) {
                       return;
                   }

                   Map<String, String> params = new HashMap<>();
                   params.put("id", id);
                   params.put("passwd", pw);
                   LoginTask loginTask = new LoginTask(MainActivity.this,params);
                   loginTask.execute(params);

                   Intent intent_join = new Intent(MainActivity.this, SubActivity.class);
                   startActivity(intent_join);
               }
           }
        });

        button_join.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_join = new Intent(MainActivity.this, JoinActivity.class);
                        startActivity(intent_join);
            }
        });
    }

    private boolean checkValid(){
        id = idEnter.getText().toString();
        pw = pwEnter.getText().toString();

        if(id == null || id.trim().equals("")){
            Toast.makeText(MainActivity.this, "학번을 입력하세요", Toast.LENGTH_SHORT).show();
            idEnter.requestFocus();
            return false;
        }
        if(pw == null || pw.trim().equals("")){
            Toast.makeText(MainActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            pwEnter.requestFocus();
            return false;
        }
        return true;
    }
}