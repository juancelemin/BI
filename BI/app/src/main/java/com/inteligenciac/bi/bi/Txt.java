package com.inteligenciac.bi.bi;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jguil on 19/03/2017.
 */

public class Txt {

    ArrayList<Question> question;

    public Txt() {
        this.question = new ArrayList<>();
    }

    public ArrayList<Question> getQuetions() {
        return question;
    }

    public void setQuetions(ArrayList<Question> question) {
        this.question = question;
    }

    private String txt (){
        String text = "";
        try{
            InputStream in = getAssets().open("questions.txt");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            text = new String(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }



    private  void makeQuestion (String text){
        Question newQuestion;
        String[]parts = text.split("-");

       // newQuestion = new Question(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],Integer.parseInt(parts[6]));
      //  question.add(newQuestion);

    }

    public void getQuestions (){
        String text = txt();
        String[]parts = text.split("_");
        // txtQuestion.setText(parts.length+ "");
        for (int i = 0; i < parts.length-1; i++){
            makeQuestion(parts[i]);
        }


    }


    public AssetManager getAssets() {
        AssetManager.AssetInputStream assets;
        return getAssets();
    }
}
