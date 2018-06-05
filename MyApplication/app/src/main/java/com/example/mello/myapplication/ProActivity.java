package com.example.mello.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mello.myapplication.Util.Sub;

public class ProActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);

        Button button_class = (Button)findViewById(R.id.open_class);
        button_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_join = new Intent(ProActivity.this, ClassActivity.class);
                startActivity(intent_join);
            }
        });
    }
}
