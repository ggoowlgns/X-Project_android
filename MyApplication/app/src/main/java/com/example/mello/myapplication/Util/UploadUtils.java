package com.example.mello.myapplication.Util;


import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

public class UploadUtils {
    private static final String TAG = UploadUtils.class.getSimpleName();

    private static final int TIME_OUT = 30 * 1000;

    private static final String CRLF = "\r\n";
    private static final String CONTENT_TYPE = "multipart/form-data";

    public static final int SUCCESS = 0;
    public static final int FAILURE = -1;
    public static final int FILE_NOT_EXIST = -2;

    public static int postFileToURL(File file, String mimeType, URL url,
                                    String fieldName) {
        if (file == null) // 그림을 선택한 후 업로드하기 전에 삭제 될 수 있으므로 다시 판단하십시오.
            return FILE_NOT_EXIST;
        try {

            String boundary = UUID.randomUUID().toString();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setHttpURLConnection(conn, boundary);
            writeData(conn, boundary, file, mimeType, fieldName);

            int res = conn.getResponseCode();
            Log.i("in?", "or out");
            if (res == 200)
                return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FAILURE;
    }

    private static void setHttpURLConnection(HttpURLConnection conn,
                                             String boundary) {
        conn.setConnectTimeout(TIME_OUT);
        conn.setReadTimeout(TIME_OUT);

        conn.setDoInput(true); // 입력 스트림 허용
        conn.setDoOutput(true); // 출력 스트림 허용

        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Type", CONTENT_TYPE + "; boundary="
                + boundary);
    }

    private static void writeData(HttpURLConnection conn, String boundary,
                                  File file, String mimeType, String fieldName) throws IOException {
        DataOutputStream requestData = new DataOutputStream(
                conn.getOutputStream());

        requestData.writeBytes("--" + boundary + CRLF);
        requestData.writeBytes("Content-Disposition: form-data; name=\""
                + fieldName + "\"; filename=\"" + file.getName() + "\"" + CRLF);
        requestData.writeBytes("Content-Type: " + mimeType + CRLF + CRLF);

        InputStream fileInput = new FileInputStream(file);
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = fileInput.read(buffer)) != -1) {
            requestData.write(buffer, 0, bytesRead);
        }
        fileInput.close();
        requestData.writeBytes(CRLF);

        requestData.writeBytes("--" + boundary + "--" + CRLF);
        requestData.flush();
    }
}

