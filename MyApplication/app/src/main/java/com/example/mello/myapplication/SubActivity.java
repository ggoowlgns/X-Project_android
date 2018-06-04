package com.example.mello.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mello.myapplication.Network.JobTask;
import com.example.mello.myapplication.Network.LoginTask;

import java.util.HashMap;
import java.util.Map;

public class SubActivity extends AppCompatActivity {
    String job;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String data = intent.getStringExtra("value");

        Button button = (Button) findViewById(R.id.btn_study);
        button.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(SubActivity.this, ReserActivity.class);
                startActivity(intent);
            }
        });

        Button button_room = (Button) findViewById(R.id.btn_room);
        button_room.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(View v)
            {
                    Map<String, String> params = new HashMap<>();
                    params.put("job", job);
                    JobTask jobTask = new JobTask(SubActivity.this,params);
                    jobTask.execute(params);
            }
        });
    }
}
