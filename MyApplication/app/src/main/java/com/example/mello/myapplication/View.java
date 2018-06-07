package com.example.mello.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

class View extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.sub);
    }
}
