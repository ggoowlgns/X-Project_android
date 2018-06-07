package com.example.mello.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mello.myapplication.Util.Sub;

import com.example.mello.myapplication.Network.LoginTask;

import java.util.HashMap;
import java.util.Map;

public class GoreserActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button button = (Button) findViewById(R.id.btn_reserve);
        Button add_button = (Button) findViewById(R.id.add_layout);

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

      //  add_button.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
        //        Sub n_layout = new Sub(getApplicationContext());
        //        LinearLayout con = (LinearLayout)findViewById(R.id.con);
         //       con.addView(n_layout);

      //          Button but = (Button)findViewById(R.id.b1);
        //        but.setOnClickListener(new View.OnClickListener() {
            //        @Override
          //          public void onClick(View v) {
          //              Toast.makeText(GoreserActivity.this, "클릭되었습니다.", Toast.LENGTH_LONG).show();
           //         }
            //    });
      //      }
    //    });

    }
}
