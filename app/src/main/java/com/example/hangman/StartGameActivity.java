package com.example.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This is the main activity that represents
 * the welcome page, choose category and start the game
 */
public class StartGameActivity extends AppCompatActivity {

    public static final String TAG = "StartGameActivity";

    private Button categories;
    private Button start, startCustom;
    private Button btn_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        String CurrentCategory = GameHandler.getCurrentCategory();

        GameHandler.resetCustomWord();

        categories = findViewById(R.id.btnCat);
        categories.setText(CurrentCategory);

        start = findViewById(R.id.btnStart);

        btn_date=findViewById(R.id.btndata);

        startCustom = findViewById(R.id.btnStartCustom);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StartGameActivity.this,GraphActivity.class);
                Log.i(TAG,"successful");
                startActivity(i);
            }
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartGameActivity.this, CategoriesActivity.class);
                i.putExtra("parent", "StartGameActivity");
                startActivity(i);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartGameActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        startCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StartGameActivity.this);
                alert.setTitle("Custom Word");
                alert.setMessage("选择一个自定义单词展示");

                final EditText input = new EditText(StartGameActivity.this);
                input.setKeyListener(DigitsKeyListener.getInstance("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));


                input.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                alert.setView(input);

                alert.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (input.length() >= 3 && input.length() <= 16) {
                            String word = input.getText().toString();
                            GameHandler.setCustomWord(word);
                            Intent i = new Intent(StartGameActivity.this, GameActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(StartGameActivity.this, "Enter word length between 3 and 16", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Nothing to do here, the dialog will close on its own
                    }
                });

                alert.show();
            }
        });

    }


}
