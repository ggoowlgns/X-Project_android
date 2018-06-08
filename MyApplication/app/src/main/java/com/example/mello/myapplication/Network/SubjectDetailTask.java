package com.example.mello.myapplication.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by JiHoon on 2018-06-09.
 */
import com.example.mello.myapplication.Util.Constants;

import java.util.Map;

public class SubjectDetailTask extends AsyncTask <Map<String, String>, Integer, String>{
    Context context;
    Map<String, String> params;
    public SubjectDetailTask(Context context, Map<String ,String> params){
        this.context = context;
        this.params = params;
    }

    @Override
    protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터

// Http 요청 준비 작업
        HttpClient.Builder http = new HttpClient.Builder("POST", Constants.isaAddr+"subject/get_subdetail");

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
