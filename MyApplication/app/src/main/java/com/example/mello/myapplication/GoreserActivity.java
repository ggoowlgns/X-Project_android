package com.example.mello.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mello.myapplication.Network.LoginTask;

import java.util.HashMap;
import java.util.Map;

public class GoreserActivity extends AppCompatActivity{
    EditText person1, person2, person3, person4, person5, person6;
    String num, two, three, four, five, six;
    private static final long MIN_CLICK_INTERVAL=600;
    private long mLastClickTime;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        person1 = (EditText)findViewById(R.id.people1);
        person2 = (EditText)findViewById(R.id.people2);
        person3 = (EditText)findViewById(R.id.people3);
        person4 = (EditText)findViewById(R.id.people4);
        person5 = (EditText)findViewById(R.id.people5);
        person6 = (EditText)findViewById(R.id.people6);

        Button button = (Button) findViewById(R.id.btn_reserve);

        Spinner spinner1 = (Spinner)findViewById(R.id.mySpinner1);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.univ, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner3 = (Spinner)findViewById(R.id.mySpinner3);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
                this, R.array.start, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner)findViewById(R.id.mySpinner4);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(
                this, R.array.end, android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkValid()) {
                    long currentClickTime = SystemClock.uptimeMillis();
                    long elapsedTime = currentClickTime - mLastClickTime;
                    mLastClickTime = currentClickTime;

                    // 중복 클릭인 경우
                    if (elapsedTime <= MIN_CLICK_INTERVAL) {
                        return;
                    }

                    Map<String, String> params = new HashMap<>();
                    params.put("human1", num);
                    params.put("human2", two);
                    params.put("human3", three);
                    params.put("human4", four);
                    params.put("human5", five);
                    params.put("human6", six);
                    LoginTask loginTask = new LoginTask(GoreserActivity.this,params);
                    loginTask.execute(params);

                }
            }
        });
    }
    private boolean checkValid(){
        num = person1.getText().toString();
        two = person2.getText().toString();
        three = person3.getText().toString();
        four = person4.getText().toString();
        five = person5.getText().toString();
        six = person6.getText().toString();

        if(num == null || num.trim().equals("")){
            Toast.makeText(GoreserActivity.this, "사용자3까지 추가해주세요", Toast.LENGTH_SHORT).show();
            person1.requestFocus();
            return false;
        }
        if(two == null || two.trim().equals("")){
            Toast.makeText(GoreserActivity.this, "사용자3까지 추가해주세요", Toast.LENGTH_SHORT).show();
            person2.requestFocus();
            return false;
        }
        if(three == null || three.trim().equals("")){
            Toast.makeText(GoreserActivity.this, "사용자3까지 추가해주세요", Toast.LENGTH_SHORT).show();
            person3.requestFocus();
            return false;
        }
        return true;
    }
}
