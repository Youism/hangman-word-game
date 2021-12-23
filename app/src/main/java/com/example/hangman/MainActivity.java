package com.example.hangman;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.login);
        Button bt2 = findViewById(R.id.visitor);
        ImageButton ibt1 = findViewById(R.id.register);
        Log.i(TAG,"start successful1! ");
        //Button的事件监听
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button跳转到显示界面
                Intent intent = new Intent(MainActivity.this,bt1Activity.class);
                Log.i(TAG,"start successful!2 ");
                startActivity(intent);
                Log.i(TAG,"start successful!3 ");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,StartGameActivity.class);
                startActivity(intent2);
            }
        });

        //ImageActionButton的事件监听
        ibt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FloatingActionButton跳转到显示界面
                Intent intent1 = new Intent(MainActivity.this,fabt1Activity.class);
                startActivity(intent1);
                Log.i(TAG,"start successful! 4");
            }
        });
    }

}
