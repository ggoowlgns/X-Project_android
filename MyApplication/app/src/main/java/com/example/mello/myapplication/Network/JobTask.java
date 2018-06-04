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
import com.example.mello.myapplication.StudentActivity;
import com.example.mello.myapplication.SubActivity;
import com.example.mello.myapplication.User.UserInfo;
import com.example.mello.myapplication.Util.Constants;


import java.util.Map;

public class JobTask extends AsyncTask <Map<String, String>, Integer, String> {
    Context context;
    Map<String, String> params;

    public JobTask(Context context, Map<String, String> params) {
        this.context = context;
        this.params = params;
    }

    protected String doInBackground(Map<String, String>... maps) {

// Http 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", Constants.isaAddr + "members/login");

// Parameter 를 전송한다.
        http.addAllParameters(maps[0]);

        Log.i("파라미터", maps[0] + "");
//Http 요청 전송


        HttpClient post = http.create();
        post.request();

// 응답 상태코드 가져오기
        int statusCode = post.getHttpStatusCode();

// 응답 본문 가져오기
        String body = post.getBody();

        return body;
    }

    protected void onPostExecute(String s) {
        Log.i("결과", s + "나옴");

        if (s.trim().equals("")) {
            context.startActivity(new Intent(context, ProActivity.class));
        } else {
            context.startActivity(new Intent(context, StudentActivity.class));
        }
    }
}
