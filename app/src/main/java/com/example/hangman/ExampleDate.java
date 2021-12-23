package com.example.hangman;


public class ExampleDate {
    private String dailyDate;
    private Float count;

    ExampleDate(String dailyDate,Float count){
        this.dailyDate=dailyDate;
        this.count=count;
    }

    String getDailyDate(){
        return this.dailyDate;
    }

    Float getCount(){
        return this.count;
    }
}
