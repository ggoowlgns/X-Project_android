package com.example.mello.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class CheckingActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        Intent intent = getIntent();
        Log.i("CheingAct",""+intent.getExtras().getString("sub_name"));
    }

}
