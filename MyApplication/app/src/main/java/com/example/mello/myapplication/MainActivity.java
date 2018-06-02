package com.example.mello.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn_login);
        Button button_join = (Button)findViewById(R.id.btnRegist);


        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
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
}