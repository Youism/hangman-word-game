package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This activity represents the hangman game
 */
public class GameActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://www.dictionaryapi.com/api/v3/references/thesaurus/json/";
    public static final String API_KEY = "?key=66fc136c-1717-437c-84fa-eb7033e37e39";
    public static final String TAG = "GameActivity";

    private TextView roundTv, wordTv;
    private Button catBtn;
    private ImageButton soundBtn, home;
    private TextView tvHint;
    private TextView tvHintText;
    private ImageView hangManImage;

    private int currentHangManState = 1; // nb of fails

    private MediaPlayer music;

    String CurrentWord = "", CurrentCategory = "";

    List<Character> GuessedWords = new ArrayList<>();

    /**
     * Hint 数据初始化
     */
    private List<String> syns = new ArrayList<>();
    private List<String> ants = new ArrayList<>();
    private Boolean offensive;
    private List<String> phrase = new ArrayList<>();
    private List<String> aggregate = new ArrayList<>();
    private List<String> badCondition = new ArrayList<>();

    //记录每个单词的数量，根据这个更新
    int numSyns=0;
    int numAnts=0;
    int numPhrase=0;
    int offensiveness = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        CurrentCategory = GameHandler.getCurrentCategory();
        CurrentWord = GameHandler.getNextWord().toUpperCase();
//        CurrentWord = "happy".toUpperCase();

        roundTv = findViewById(R.id.roundTv);

        roundTv.setText("胜场: " + GameHandler.getStreak());

        wordTv = findViewById(R.id.word);

        catBtn = findViewById(R.id.catBtn);

        tvHint = findViewById(R.id.tv_hint);

        tvHintText = findViewById(R.id.tv_hint_text);

        catBtn.setEnabled(false);


        if (GameHandler.currentCustomWord.isEmpty()) {
            catBtn.setText(CurrentCategory);
        } else {
            catBtn.setText("自定义单词");
        }

        hangManImage = findViewById(R.id.hangImage);

        UpdateWord();

        //回到主页面
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameActivity.this, MainActivity.class);
                music.stop();
                startActivity(i);
            }
        });


        //init
        initHint(CurrentWord.toLowerCase());


        //单词button
        Button[] buttons = new Button[]{
                findViewById(R.id.btnA), findViewById(R.id.btnB), findViewById(R.id.btnC),
                findViewById(R.id.btnD), findViewById(R.id.btnE), findViewById(R.id.btnF),
                findViewById(R.id.btnG), findViewById(R.id.btnH), findViewById(R.id.btnI),
                findViewById(R.id.btnJ), findViewById(R.id.btnK), findViewById(R.id.btnL),
                findViewById(R.id.btnD), findViewById(R.id.btnE), findViewById(R.id.btnF),
                findViewById(R.id.btnG), findViewById(R.id.btnH), findViewById(R.id.btnI),
                findViewById(R.id.btnM), findViewById(R.id.btnN), findViewById(R.id.btnO),
                findViewById(R.id.btnP), findViewById(R.id.btnQ), findViewById(R.id.btnR),
                findViewById(R.id.btnS), findViewById(R.id.btnT), findViewById(R.id.btnU),
                findViewById(R.id.btnV), findViewById(R.id.btnW), findViewById(R.id.btnX),
                findViewById(R.id.btnY), findViewById(R.id.btnZ)};

        updateHint();
        for (Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    updateHint();
                    char character = btn.getText().toString().charAt(0);
                    Log.i(TAG,"User clicked "+character);
                    if (IsCharValid(character)) {
                        btn.setTextColor(Color.parseColor("#88E684"));
                        GuessedWords.add(character);
                        Log.i(TAG,"User do a good job");
                        UpdateWord();
                    } else {
                        currentHangManState++;

                        //TODO:更新提示
                        //TODO:选择前三个近义词/反义词/短词/褒义贬义

                        Log.i(TAG,"Bad");
                        btn.setTextColor(Color.parseColor("#C9132A"));
                        UpdateImage();
//                        updateHint();

                        //Vibrate On Wrong Character
                        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                        if (!(am.getRingerMode() == AudioManager.RINGER_MODE_SILENT)) {
                            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                //deprecated in API 26
                                v.vibrate(500);
                            }
                        }
                    }
                    btn.setEnabled(false);
                    CheckGameState();//Make Sure The Game Hasn't Ended
                }
            });
        }

        music = MediaPlayer.create(GameActivity.this, R.raw.rope_music);
        music.setVolume(0.3f, 0.3f);
        music.setLooping(true);

        soundBtn = findViewById(R.id.btnSound);

        if (GameHandler.areSoundsOn == false) {
            soundBtn.setImageResource(R.drawable.sound_off);
        }

        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameHandler.areSoundsOn) {
                    music.pause();
                    soundBtn.setImageResource(R.drawable.sound_off);
                    GameHandler.areSoundsOn = false;
                } else {
                    music.start();
                    soundBtn.setImageResource(R.drawable.sound_on);
                    GameHandler.areSoundsOn = true;
                }
            }
        });
    }

    /**
     * initialize hint text
     *
     * @param word
     */
    private void initHint(String word) {

        String result = getResultJSON(word);
        JSONProcessor jsonProcessor = new JSONProcessor(result);
        syns = jsonProcessor.getSyns();
        ants = jsonProcessor.getAnts();
        offensive = jsonProcessor.getOffensive();
        phrase = jsonProcessor.getPhrase();
        badCondition = jsonProcessor.getBadCondition();
        if (syns != null)
            numSyns = Math.min(3, syns.size());
        if (ants != null)
            numAnts = Math.min(3, ants.size());
        if (phrase != null)
            numPhrase = Math.min(3, phrase.size());
//        Log.d(TAG, "Syns is" + syns.get(0));
        return;
    }

    /**
     * get the JSON file ,and then, transfer the data to the word data object
     *
     * @param word
     * @return
     */
    public String getResultJSON(String word) {

        String url = BASE_URL + word + API_KEY;
        String received = null;
        Future<String> s = Ion.with(GameActivity.this).load(url).asString();
        try {
            received = s.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return received;
    }

    /**
     * update textview in the activity
     * <p>
     * 每种选择三个
     */
    private void updateHint() {
        if (syns == null && ants == null) {
            badCase();
            return;
        }
        tvHintText = findViewById(R.id.tv_hint_text);
        //褒义词
        if (offensiveness == 1) {
            String f;
            if (offensive) f = "贬义词\uD83D\uDE04";
            else f = "褒义词\uD83D\uDE04";
            tvHintText.setText("词性：" + f);
            offensiveness--;
            return;
        }
        //近义词
        else if (numSyns > 0) {

            Random rand = new Random();
            String randomElement = syns.get(rand.nextInt(syns.size()));
            syns.remove(new String(randomElement));
            numSyns--;
            Log.i(TAG,"numSyns is "+numSyns);
            String s = "近义词\uD83D\uDE00：" + randomElement;
            tvHintText.setText(s);
            return;
        }

        //反义词
        else if (numAnts > 0) {
            Random rand = new Random();
            String randomElement = ants.get(rand.nextInt(ants.size()));
            ants.remove(new String(randomElement));
            numAnts--;
            Log.i(TAG,"numAnts is "+numAnts);
            Log.i(TAG,"numPhrase is "+numPhrase);
            String s = "反义词\uD83E\uDD2A：" + randomElement;
            tvHintText.setText(s);
            return;
        }
        //短语--;
        else if (numPhrase > 0) {
            Random rand = new Random();
            String randomElement = phrase.get(rand.nextInt(phrase.size()));
            phrase.remove(new String(randomElement));
            numPhrase--;
            String s = "短语\uD83D\uDE1D：" + randomElement;
            tvHintText.setText(s);
            return;
        } else {
            return;
        }
    }

    /**
     * 处理API只返回[...]的情况
     */
    private void badCase() {
        tvHintText = findViewById(R.id.tv_hint_text);
        if (badCondition == null) {
            return;
        }
        Random rand = new Random();
        String randomElement = badCondition.get(rand.nextInt(badCondition.size()));
        String s = "相关单词\uD83D\uDE0F：" + randomElement;
        tvHintText.setText(s);

        return;
    }

    /**
     * This method updates the image after each fail
     */
    public void UpdateImage() {
        switch (currentHangManState) {
            case 2:
                hangManImage.setImageResource(R.drawable.hangman2);
                break;
            case 3:
                hangManImage.setImageResource(R.drawable.hangman3);
                break;
            case 4:
                hangManImage.setImageResource(R.drawable.hangman4);
                break;
            case 5:
                hangManImage.setImageResource(R.drawable.hangman5);
                break;
            case 6:
                hangManImage.setImageResource(R.drawable.hangman6);
                break;
            case 7:
                hangManImage.setImageResource(R.drawable.hangman7);
                break;
            case 8:
                hangManImage.setImageResource(R.drawable.hangman8);
                break;
            default:
                hangManImage.setImageResource(R.drawable.hangman1);
                break;
        }

    }

    /**
     * This method checks if the character entered by user is valid or not
     *
     * @param character character entered by user
     * @return boolean if valid or not
     */
    public Boolean IsCharValid(char character) {
        char[] charArray = CurrentWord.toCharArray();
        for (char c : charArray) {
            if (c ==  character) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method updates word after each character entered by user is correct
     */
    public void UpdateWord() {
        String text = "";
        for (int i = 0; i < CurrentWord.length(); i++) {
            if (GuessedWords.contains(CurrentWord.charAt(i))) {
                text += CurrentWord.charAt(i) + " ";
                wordTv.setText(text);
            } else {
                text += "_ ";
                wordTv.setText(text);
            }
        }
    }

    /**
     * This method checks if the user won or lose
     */
    public void CheckGameState() {
        if (CurrentWord.equalsIgnoreCase(wordTv.getText().toString().replace(" ", ""))) {
            Intent i = new Intent(GameActivity.this, EndGameActivity.class);
            i.putExtra("guessedWord", CurrentWord);
            i.putExtra("won", "true");
            GameHandler.updateStreak(true);
            music.stop();
            startActivity(i);
        } else if (currentHangManState == 8) {//Game Lost
            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(1000);
            }
            Intent i = new Intent(GameActivity.this, EndGameActivity.class);
            i.putExtra("guessedWord", CurrentWord);
            i.putExtra("won", "false");
            GameHandler.updateStreak(false);
            music.stop();
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {//To Prevent Loading Earlier Intent States
        Intent setIntent = new Intent(this, MainActivity.class);
        music.stop();
        startActivity(setIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (GameHandler.areSoundsOn)
            music.start();
    }
}