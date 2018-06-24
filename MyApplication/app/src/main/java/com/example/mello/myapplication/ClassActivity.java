package com.example.mello.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.example.mello.myapplication.Network.SubjectTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.mello.myapplication.R.layout.sub;

public class ClassActivity extends AppCompatActivity{
    Toolbar signToolbar; // 회원가입 툴바
    EditText enterId, subEnter;
    String id_num, sub_name, pro_name;
    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;
    private String[] iden = {};
    final ArrayList<String> idList = new ArrayList<String>(Arrays.asList(iden));
    ArrayAdapter<String> adapter;
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

        idText = (EditText)findViewById(R.id.rg_number);
        //idList.addAll(Arrays.asList(iden));

        adapter = new ArrayAdapter<String>(this,R.layout.makepont,idList);

        ListView list = (ListView)findViewById(R.id.con);
        list.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.added);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ClassActivity.this, "추가되었습니다.", Toast.LENGTH_LONG).show();
                idList.add(0, idText.getText().toString());
                adapter.notifyDataSetChanged();
                idText.setText("");
                idText.setHint("학번입력");
            }

        });


        Button button_make = (Button)findViewById(R.id.Complete);
        button_make.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", 0);

                pro_name = sharedPreferences.getString("name", null);
                id_num = idList.toString();
                sub_name = subEnter.getText().toString();

                long currentClickTime= SystemClock.uptimeMillis();
                long elapsedTime=currentClickTime-mLastClickTime;
                mLastClickTime=currentClickTime;

                // 중복 클릭인 경우
                if(elapsedTime<=MIN_CLICK_INTERVAL){
                    return;
                }
                String send_stu = "";
                //for-loop 통한 전체 조회
                for(Object object : idList) {
                    String element = (String) object;
                    send_stu += element +"/";
                }
                Log.i("stu :", "" +send_stu);
                SubjectTask subjectTask = new SubjectTask(ClassActivity.this);
                Map<String, String> params = new HashMap<>();
                params.put("id_num", send_stu);
                params.put("sub_name", sub_name);
                params.put("pro_name", pro_name);
                subjectTask.execute(params);

                Intent intent = new Intent(ClassActivity.this, ProActivity.class);
                intent.putExtra("sub_name", sub_name);
                startActivity(intent);
            }
        });
    }

}
