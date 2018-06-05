package com.example.mello.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.mello.myapplication.Network.SubjectTask;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClassActivity extends AppCompatActivity{
    Toolbar signToolbar; // 회원가입 툴바
    EditText enterId, subEnter;
    String id_num, sub_name;
    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;
    private String[] iden = {"201402239"};

    ArrayAdapter<String> adapter;
    ArrayList<String> idList;
    EditText idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        signToolbar = (Toolbar) findViewById(R.id.signtool);
        enterId = (EditText)findViewById(R.id.rg_number);
        subEnter = (EditText)findViewById(R.id.Class_name);
        setSupportActionBar(signToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        idText = (EditText)findViewById(R.id.rg_number);
        //idList.addAll(Arrays.asList(iden));
        final ArrayList<String> idList = new ArrayList<String>(Arrays.asList(iden));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,idList);

        ListView list = (ListView)findViewById(R.id.con);
        list.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.added);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ClassActivity.this, "추가되었습니다.", Toast.LENGTH_LONG).show();

                idList.add(0, idText.getText().toString());
                adapter.notifyDataSetChanged();
            }

        });

        Button button_make = (Button)findViewById(R.id.Complete);
        button_make.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                id_num = enterId.getText().toString();
                sub_name = subEnter.getText().toString();

                long currentClickTime= SystemClock.uptimeMillis();
                long elapsedTime=currentClickTime-mLastClickTime;
                mLastClickTime=currentClickTime;

                // 중복 클릭인 경우
                if(elapsedTime<=MIN_CLICK_INTERVAL){
                    return;
                }
                SubjectTask subjectTask = new SubjectTask(ClassActivity.this);
                Map<String, String> params = new HashMap<>();
                params.put("id_num", id_num);
                params.put("sub_name", sub_name);
                subjectTask.execute(params);

            }
        });
    }
}
