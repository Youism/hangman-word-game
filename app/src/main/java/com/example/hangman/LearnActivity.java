package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Button bt2 = findViewById(R.id.button2);
        Button bt3 = findViewById(R.id.button3);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbt2 = new Intent(LearnActivity.this, KnowActivity.class);
                startActivity(intentbt2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbt3 = new Intent(LearnActivity.this,NotknowActivity.class);
                startActivity(intentbt3);
            }
        });
    }
}