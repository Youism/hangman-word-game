package com.example.hangman;

import com.robinhood.spark.SparkAdapter;

import java.util.ArrayList;

class MySparkAdapter extends SparkAdapter {
    private ArrayList<ExampleDate> dateList;
    public MySparkAdapter(ArrayList<ExampleDate> dateList) {
        this.dateList=dateList;
    }

    @Override
    public int getCount() {
        return this.dateList.size();
    }

    @Override
    public Object getItem(int index) {
        return this.dateList.get(index);
    }

    @Override
    public float getY(int index) {
        ExampleDate date=this.dateList.get(index);
        return date.getCount();
    }
}
