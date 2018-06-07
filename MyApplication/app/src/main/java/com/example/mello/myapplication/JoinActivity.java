package com.example.mello.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mello.myapplication.Network.SignTask;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    Toolbar signToolbar; // 회원가입 툴바
    Button signOkBtn; // 확인버튼
    //이메일, 비밀번호, 비밀번호 확인, 이름 입력
    EditText idEdit, pwEdit, phoneEdit, nameEdit, jobEdit;
    String id, pw, phone, name, job_final;

    private static final long MIN_CLICK_INTERVAL=600;

    private long mLastClickTime;
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
        final Spinner spinner_job = (Spinner)findViewById(R.id.mySpinner_job);
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
                    params.put("job", job_final);

                    signTask.execute(params);

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
}
