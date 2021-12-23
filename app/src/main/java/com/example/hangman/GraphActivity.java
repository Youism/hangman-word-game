package com.example.hangman;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.robinhood.spark.SparkView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class GraphActivity extends AppCompatActivity {
    private TextView tvDate;
    private TickerView tickerView;
    private SparkView sparkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        tickerView = findViewById(R.id.tickerView);
        tvDate = findViewById(R.id.tv_date);
        sparkView = findViewById(R.id.spark_body);

        String tmp = "2021/2/";
        ArrayList<ExampleDate> dateList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            int plusNumber = i % 31;
            String t = tmp + plusNumber;
            int r = random.nextInt(500);
            ExampleDate date = new ExampleDate(t, new Float(r));
            dateList.add(date);
        }
        for (int i = 0; i < 100; ++i) {
            int plusNumber = i % 31;
            String t = tmp + plusNumber;
            int r = random.nextInt(1000);
            ExampleDate date = new ExampleDate(t, new Float(r));
            dateList.add(date);
        }

        setupEventListeners();
        updateForDisplayWithDate(dateList);
    }

    private void setupEventListeners() {
        sparkView.setScrubEnabled(true);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        sparkView.setScrubListener(new SparkView.OnScrubListener() {
            @Override
            public void onScrubbed(Object value) {
                if (value instanceof ExampleDate) {
                    updateInfo((ExampleDate) value);
                }
            }
        });
    }

    private void updateForDisplayWithDate(ArrayList<ExampleDate> dateList) {
        MySparkAdapter adapter = new MySparkAdapter(dateList);
        sparkView.setAdapter(adapter);
        int colorInt = ContextCompat.getColor(this, R.color.design_default_color_primary);
        sparkView.setLineColor(colorInt);
        tickerView.setTextColor(colorInt);
        updateInfo(dateList.get(dateList.size() - 1));

    }

    private void updateInfo(ExampleDate exampleDate) {
        tvDate.setText(exampleDate.getDailyDate());
        tickerView.setText(NumberFormat.getInstance().format(exampleDate.getCount()));

    }

}
