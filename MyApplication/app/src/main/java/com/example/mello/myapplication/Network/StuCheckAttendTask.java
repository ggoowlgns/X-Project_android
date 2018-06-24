package com.example.mello.myapplication.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.mello.myapplication.Util.Constants;

import java.util.Map;

/**
 * Created by JiHoon on 2018-06-24.
 */

public class StuCheckAttendTask extends AsyncTask<Map<String, String>, Integer, String> {
    Context context;
    Map<String, String> params;
    ArrayAdapter<String> adapter;
    public StuCheckAttendTask(Context context, Map<String ,String> params){
        this.context = context;
        this.params = params;
    }



    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터
        Log.i("SubjectDetailTask","SubjectDetailTask: ");
// Http 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", Constants.isaAddr+"student/check_stu");

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
    @Override
    protected void onPostExecute(String s) {

        Log.i("GetSujectDetail","GetSujectDetail: "+s);
    }
}
