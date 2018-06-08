package com.example.mello.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mello.myapplication.Util.Sub;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class ProActivity extends AppCompatActivity {
    Intent intent_name;
    String name;
    private String[] count = {};
    ArrayList<String> subList = new ArrayList<String>(Arrays.asList(count));
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);

//        intent_name = getIntent();
//        name = intent_name.getStringExtra("sub_name");
//        Log.i("과목",""+count.toString());
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subList);
//        ListView list = (ListView)findViewById(R.id.school);
//        list.setAdapter(adapter);

        if(name != null){
//            Log.i("이름",""+name);
//            View textView = (View)findViewById(R.id.main_layout_textview);
//            TextView tv01 = (TextView) textView.findViewById(R.id.subject);
//            View Button = (View)findViewById(R.id.main_layout_button);
//            Button b01 = (Button)findViewById(R.id.layout_button_02);
//            tv01.setText(name);
            subList.add(0, name.toString());
            adapter.notifyDataSetChanged();
        }
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
