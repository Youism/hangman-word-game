package com.example.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
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
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button categories;
    private Button start, startCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String CurrentCategory = GameHandler.getCurrentCategory();

        GameHandler.resetCustomWord();

        categories = findViewById(R.id.btnCat);
        categories.setText(CurrentCategory);

        start = findViewById(R.id.btnStart);

        startCustom = findViewById(R.id.btnStartCustom);

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
                i.putExtra("parent", "MainActivity");
                startActivity(i);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        startCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Custom Word");
                alert.setMessage("选择一个自定义单词展示");

                final EditText input = new EditText(MainActivity.this);
                input.setKeyListener(DigitsKeyListener.getInstance("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));


                input.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                alert.setView(input);

                alert.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (input.length() >= 3 && input.length() <= 16) {
                            String word = input.getText().toString();
                            GameHandler.setCustomWord(word);
                            Intent i = new Intent(MainActivity.this, GameActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "Enter word length between 3 and 16", Toast.LENGTH_SHORT).show();
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


//    /**
//     * get the JSON file ,and then, transfer the data to the word data object
//     *
//     * @param word
//     * @return
//     */
//    public String getResultJSON(String word) {
//
//        String url = BASE_URL + word + API_KEY;
//        String received = null;
//        Future<String> s = Ion.with(MainActivity.this).load(url).asString();
//        try {
//            received = s.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return received;
//    }
}