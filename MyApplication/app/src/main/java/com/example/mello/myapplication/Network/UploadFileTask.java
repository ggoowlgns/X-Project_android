package com.example.mello.myapplication.Network;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.mello.myapplication.Util.UploadUtils;

public class UploadFileTask extends AsyncTask<String, Void, Integer> {
    private static final String[] MSG = {"성공！", "업로드하지 못했습니다.！", "파일이 존재하지 않습니다.！"};

    private Activity mActivity;
    private ProgressDialog mProgressDialog;

    public UploadFileTask(Activity activity) {
        mActivity = activity;
        mProgressDialog = ProgressDialog.show(mActivity, "업로드 중...",
                "시스템에서 요청을 처리 중입니다.");
    }

    @Override
    protected Integer doInBackground(String... params) {
        try {
            Log.i("url : ","" +params[0]);
            Log.i("url : ","" +params[2]);
            Log.i("url : ","" +params[3]);
            return UploadUtils.postFileToURL(new File(params[0]), params[1],
                    new URL(params[2]), params[3], params[4]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return UploadUtils.FAILURE;
    }

    @Override
    protected void onPostExecute(Integer result) {
        mProgressDialog.dismiss();
        Toast.makeText(mActivity, MSG[-result], Toast.LENGTH_LONG).show();
    }
}