package com.inteligenciac.bi.bi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Button btnA, btnB, btnC, btnD;
    TextView txtQuestion;
    ArrayList<Question> question  = new ArrayList<>();
    Question currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnA = (Button)findViewById(R.id.btnA);
        btnB = (Button)findViewById(R.id.btnB);
        btnC = (Button)findViewById(R.id.btnC);
        btnD = (Button)findViewById(R.id.btnD);

        txtQuestion = (TextView)findViewById(R.id.txtQuestion);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        getQuestions();



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnA:
                isCorrect(0);
                break;
            case R.id.btnB:
                isCorrect(1);
                break;
            case R.id.btnC:
                isCorrect(2);
                break;
            case R.id.btnD:
                isCorrect(3);
                break;
        }

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
        newQuestion = new Question(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],Integer.parseInt(parts[6]));
        question.add(newQuestion);

    }

    private void showQuestion (Question newQuestion){

        txtQuestion.setText(newQuestion.getStatement());
        btnA.setText(newQuestion.getAns1());
        btnB.setText(newQuestion.getAns2());
        btnC.setText(newQuestion.getAns3());
        btnD.setText(newQuestion.getAns4());

    }
    public void getQuestions (){
        String text = txt();
        String[]parts = text.split("_");
       // txtQuestion.setText(parts.length+ "");
        for (int i = 0; i < parts.length-1; i++){
            makeQuestion(parts[i]);
        }
        nextQuestion();

    }
    private void nextQuestion (){

        Question nextQuestion;
        int size = question.size();
        Random rnd = new Random();
        int nQuestion = (int)(rnd.nextDouble() * size);
        nextQuestion = question.get(nQuestion);
        showQuestion(nextQuestion);
        currentQuestion = nextQuestion;
    }
    private void isCorrect (int ans){
        if (currentQuestion.getCorrectAns() == ans || currentQuestion.getCorrectAns() == 4){
            nextQuestion();
        }
        else{

        }
    }

}

