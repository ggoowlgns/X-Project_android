package com.example.mello.myapplication.Network;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.mello.myapplication.MainActivity;
import com.example.mello.myapplication.ProActivity;
import com.example.mello.myapplication.SubActivity;
import com.example.mello.myapplication.User.UserInfo;
import com.example.mello.myapplication.Util.Constants;
import com.example.mello.myapplication.SubActivity;

import java.util.Map;

public class SubjectTask extends AsyncTask <Map<String, String>, Integer, String>{
    Context context;

    public SubjectTask(Context context){
        this.context = context;

        }

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터

// Http 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", Constants.isaAddr+"members/login");

// Parameter 를 전송한다.
        http.addAllParameters(maps[0]);

        Log.i("파라미터", maps[0] +"");
//Http 요청 전송


        HttpClient post = http.create();
        post.request();

// 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();

// 응답 본문 가져오기
        String body = post.getBody();

        return body;
    }
}