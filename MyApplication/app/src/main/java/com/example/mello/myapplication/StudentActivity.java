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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.mello.myapplication.Util.Constants.isaAddr;
import static com.example.mello.myapplication.Util.Constants.isaFtpAddr;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", 0);
        String name = sharedPreferences.getString("name", null);
        String id = sharedPreferences.getString("id", null);
        Log.i("이름",""+ name);
        Log.i("학번",""+ id);
        TextView rl = (TextView)findViewById(R.id.etName);
        rl.setText("이름: " + name);
        TextView hak = (TextView)findViewById(R.id.etId);
        hak.setText("학번: " + id);


//        Button check = (Button)findViewById(R.id.btn_check);
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(id == null || id.trim().equals("")){
//                    Toast.makeText(StudentActivity.this, "출석되었습니다", Toast.LENGTH_SHORT).show();
//                    idEdit.requestFocus();
//                    return false;
//                }
//                if(pw == null || pw.trim().equals("")){
//                    Toast.makeText(StudentActivity.this, "출석되지 않았습니다", Toast.LENGTH_SHORT).show();
//                    pwEdit.requestFocus();
//                    return false;
//                }
//            }
//        });
//
    }


}

