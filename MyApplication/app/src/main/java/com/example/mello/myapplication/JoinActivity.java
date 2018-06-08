package com.example.mello.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.webkit.MimeTypeMap;
=======
import android.widget.AdapterView;
>>>>>>> 3672e42f71b7a8714b1f996cf4704d28e0d9768d
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mello.myapplication.Network.SignTask;
import com.example.mello.myapplication.ModelClass.EventModel;
import com.example.mello.myapplication.Network.UploadFileTask;
import com.example.mello.myapplication.Util.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    Toolbar signToolbar; // 회원가입 툴바
    Button signOkBtn; // 확인버튼
    //이메일, 비밀번호, 비밀번호 확인, 이름 입력
    EditText idEdit, pwEdit, phoneEdit, nameEdit, jobEdit;
<<<<<<< HEAD
    String id, pw, phone, name;
    private TextView responseTextView;
    private ImageView imageView;
=======
    String id, pw, phone, name, job_final;
>>>>>>> 3672e42f71b7a8714b1f996cf4704d28e0d9768d

    private static final long MIN_CLICK_INTERVAL=600;

    private long mLastClickTime;

    private static final String FIELD_NAME = "imagefile";

    private String filePath;
    private String mimeType;

    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = "Response from Server:\n" + event.getMessage();
            responseTextView.setText(responseMessage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        signToolbar = (Toolbar)findViewById(R.id.signtool);
        signOkBtn = (Button)findViewById(R.id.signOkBtn);
        idEdit = (EditText)findViewById(R.id.signId);
        pwEdit = (EditText)findViewById(R.id.signPw);
        nameEdit = (EditText)findViewById(R.id.signName);
        phoneEdit = (EditText)findViewById(R.id.signPhone);
<<<<<<< HEAD
        imageView = (ImageView) findViewById(R.id.imageView);
        responseTextView = (TextView) findViewById(R.id.responseTextView);

        verifyStoragePermissions(this);
        Spinner spinner_job = (Spinner)findViewById(R.id.mySpinner_job);
=======
        final Spinner spinner_job = (Spinner)findViewById(R.id.mySpinner_job);
>>>>>>> 3672e42f71b7a8714b1f996cf4704d28e0d9768d
        setSupportActionBar(signToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);


        ArrayAdapter<CharSequence> adapter_job = ArrayAdapter.createFromResource(this, R.array.job_name,
                android.R.layout.simple_spinner_item);
        adapter_job.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_job.setAdapter(adapter_job);



        spinner_job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               // Toast.makeText(JoinActivity.this, spinner_job.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                final String job = spinner_job.getSelectedItem().toString();
                Log.i("직업", job);
                job_final = job;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        /**
         * 확인버튼 이벤트처리
         */
        signOkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkValid()){
                    long currentClickTime= SystemClock.uptimeMillis();
                    long elapsedTime=currentClickTime-mLastClickTime;
                    mLastClickTime=currentClickTime;

                    // 중복 클릭인 경우
                    if(elapsedTime<=MIN_CLICK_INTERVAL){
                        return;
                    }


                    SignTask signTask = new SignTask(JoinActivity.this);
                    Map<String, String> params = new HashMap<>();
                    params.put("id", id);
                    params.put("passwd", pw);
                    params.put("name", name);
                    params.put("phone_num",phone);
<<<<<<< HEAD
                    params.put("job", job);
                    params.put("attend", "0");
=======
                    params.put("job", job_final);

>>>>>>> 3672e42f71b7a8714b1f996cf4704d28e0d9768d
                    signTask.execute(params);
                    Log.i("mime :",mimeType);

                    new UploadFileTask(JoinActivity.this).execute(filePath, mimeType, Constants.isaAddr+"photo/upload", FIELD_NAME );

                }
            }
        });
    }

    /**
     * 회원가입 유효성 검사
     * @return 올바름 여부
     */
    private boolean checkValid(){
        id = idEdit.getText().toString();
        pw = pwEdit.getText().toString();
        phone = phoneEdit.getText().toString();
        name = nameEdit.getText().toString();

        if(id == null || id.trim().equals("")){
            Toast.makeText(JoinActivity.this, "학번을 입력하세요", Toast.LENGTH_SHORT).show();
            idEdit.requestFocus();
            return false;
        }
        if(pw == null || pw.trim().equals("")){
            Toast.makeText(JoinActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            pwEdit.requestFocus();
            return false;
        }
        if(name == null || name.trim().equals("")){
            Toast.makeText(JoinActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            nameEdit.requestFocus();
            return false;
        }
        if(phone == null || phone.trim().equals("")){
            Toast.makeText(JoinActivity.this, "폰번호를 입력하세요", Toast.LENGTH_SHORT).show();
            phoneEdit.requestFocus();
            return false;
        }
//        if(!pw.equals(rePw)){
//            Toast.makeText(JoinActivity.this, "비밀번호가 일치해야 합니다", Toast.LENGTH_SHORT).show();
//            rePwEdit.requestFocus();
//            return false;
//        }
        return true;
    }
    /**
     * 툴바 뒤로가기 셋팅
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            imageView.setImageURI(imageUri);
            Log.i("imageUri: ",filePath);
            mimeType = MimeTypeMap.getSingleton()
                    .getMimeTypeFromExtension(
                            MimeTypeMap.getFileExtensionFromUrl(filePath));
        }
    }


    //안돌아가는 부분 그냥 가져온거
    public void uploadButtonClicked(View view) {
        String mimeType = MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(
                        MimeTypeMap.getFileExtensionFromUrl(filePath));

        new UploadFileTask(this).execute(filePath, mimeType, Constants.isaAddr, FIELD_NAME );

        //NetworkCall.fileUpload(filePath, new ImageSenderInfo(name, age));
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        Log.i("path: ",cursor.getString(column_index));
        return cursor.getString(column_index);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
