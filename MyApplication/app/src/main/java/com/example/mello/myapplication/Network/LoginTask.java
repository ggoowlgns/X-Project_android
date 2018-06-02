package com.example.mello.myapplication.Network;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mello.myapplication.MainActivity;
import com.example.mello.myapplication.SubActivity;
import com.example.mello.myapplication.User.UserInfo;
import com.example.mello.myapplication.Util.Constants;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by JiHoon on 2018-06-03.
 */

public class LoginTask extends AsyncTask <Map<String, String>, Integer, String>{
    Context context;
    Map<String, String> params;
    public LoginTask(Context context, Map<String ,String> params){
        this.context = context;
        this.params = params;
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

    @Override
    protected void onPostExecute(String s) {
        Log.i("결과", s+"나옴");

        if(s.trim().equals("")) {
            Toast.makeText(context, "회원 정보가 없거나 일치하지 않습니다.", Toast.LENGTH_LONG).show();
        }else {

            try {
                Log.i("in","sd");

                //자동로그인 등록
                SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String loginString = sharedPreferences.getString("id", null);
                if(loginString == null){
                    editor.putString("id", params.get("id"));
                    editor.putString("passwd", params.get("passwd"));
                    editor.commit();
                }
                UserInfo.id = params.get("id");
                context.startActivity(new Intent(context, SubActivity.class));

            }catch(Exception e){
                Log.e("JSONObject", s + " paserse exception!");
            }

        }
    }


}
