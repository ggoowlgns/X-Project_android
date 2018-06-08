package com.example.mello.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mello.myapplication.Network.GetSubjectTask;
import com.example.mello.myapplication.Util.Sub;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class ProActivity extends AppCompatActivity {
    String name;
    private String[] count = {};
    ArrayList<String> subList = new ArrayList<String>(Arrays.asList(count));
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subList);
        ListView list = (ListView)findViewById(R.id.school);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProActivity.this, CheckingActivity.class);

                intent.putExtra("sub_name", subList.get(i));

                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", 0);
        String pro_name = sharedPreferences.getString("name", null);

        Map<String, String> params = new HashMap<>();
        params.put("pro_name", pro_name);
        GetSubjectTask getSubjectTask = new GetSubjectTask(ProActivity.this, params);
        String temp="No Lecture";
        try {
            temp = getSubjectTask.execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        Log.i("ProAct", "temp: "+temp);
        if(!temp.equals("No Lecture")){
            String[] lec_arry= temp.split("/");
            for(String lec :lec_arry){
                Log.i("ProAct ", "lec : "+lec);
                subList.add(0, lec);
            }
        }
        adapter.notifyDataSetChanged();


        Button button_class = (Button)findViewById(R.id.open_class);
        button_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_join = new Intent(ProActivity.this, ClassActivity.class);
                startActivity(intent_join);
            }
        });
    }

}
