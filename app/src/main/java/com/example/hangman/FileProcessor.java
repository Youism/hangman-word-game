package com.example.hangman;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *  File class : process dictionary file
 */
public class FileProcessor {
    public static final String ASSETS_WORDS = "words";
    private ArrayList<String> rt;
    private String fullName ;

    FileProcessor(String specificName){
        fullName=ASSETS_WORDS+"/"+specificName;
        rt=new ArrayList<>();
    }

    public String getFullName(){
        return this.fullName;
    }

    /**
     * return list of words
     * @param context
     * @return
     */
    public ArrayList<String> fromNameToList(Context context) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(getFullName()), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                this.rt.add(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }
        return this.rt;
    }

    /**
     * get a random word from a specific category
     * @return word String
     */
    String getRandomWord(){
        Random rand = new Random();
        String randomElement = this.rt.get(rand.nextInt(this.rt.size()));
        return randomElement;
    }
}
