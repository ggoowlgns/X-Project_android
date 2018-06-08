package com.example.mello.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.mello.myapplication.User.UserInfo;

public class SubActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button button = (Button) findViewById(R.id.btn_study);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, ReserActivity.class);
                startActivity(intent);
            }
        });

        Button button_room = (Button) findViewById(R.id.btn_room);
        button_room.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", 0);
                String job = sharedPreferences.getString("job", null);
                Log.i("직업",""+ job);
                if (job.equals("교수")){
                    Intent intent_pro = new Intent(SubActivity.this, ProActivity.class);
                    startActivity(intent_pro);
                }else{
                    Intent intent_stu = new Intent(SubActivity.this, StudentActivity.class);
                    startActivity(intent_stu);
                }
            }
        });
    }

}
