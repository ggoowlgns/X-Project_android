package com.example.mello.myapplication;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.mello.myapplication.Util.Constants.isaAddr;
import static com.example.mello.myapplication.Util.Constants.isaFtpAddr;

public class StudentActivity extends AppCompatActivity {
    ImageView imView;
    Bitmap bmImg;
//    back task;
    String imgUrl = isaAddr;
    File imgFile = new  File(imgUrl + "/face/get/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Log.i("먼가","" + imgFile);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            ImageView myImage = (ImageView) findViewById(R.id.image_std);

            myImage.setImageBitmap(myBitmap);

            Log.i("사진", ""+myImage);

        }

//        task = new back();
//        task.execute(imgUrl+"20160140.jpg");

    }

//    private class back extends AsyncTask<String, Integer, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            // TODO Auto-generated method stub
//            try {
//                URL myFileUrl = new URL(urls[0]);
//                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                bmImg = BitmapFactory.decodeStream(is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bmImg;
//        }
//
//        protected void onPostExecute(Bitmap img) {
//            imView.setImageBitmap(bmImg);
//            Log.i("여기", ""+imView);
//        }
//
//    }

}