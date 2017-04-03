package com.inteligenciac.bi.bi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    Button btnA, btnB, btnC, btnD,btnE, btnF;
    ImageButton btnListA, btnListB,btnListC,btnListD,btnListE,btnListF;
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
        btnE = (Button)findViewById(R.id.btnE);
        btnF = (Button)findViewById(R.id.btnF);




        txtQuestion = (TextView)findViewById(R.id.txtQuestion);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnD.destroyDrawingCache();

        getQuestions();


    }

    @Override
    public void onClick(View v) {
        int color = Color.parseColor("#9b00e2");
        View contenedor = v.getRootView();
        contenedor.setBackgroundColor(color);
        switch (v.getId()){

            case R.id.btnA:

                break;
            case R.id.btnB:

                break;
            case R.id.btnC:

                break;
            case R.id.btnD:

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
        Question newQuestion = null;
        String[]parts = text.split("-");

        newQuestion = new Question(parts[0],
                makeAnswer(parts[1]),
                makeAnswer(parts[2]),
                makeAnswer(parts[3]),
                makeAnswer(parts[4]),
                makeAnswer(parts[5]),
                makeAnswer(parts[6]),
                parts[7]);
        question.add(newQuestion);

    }
    private  Answer makeAnswer (String ans){

        String parts [] = ans.split(",,");
        Answer answer = new Answer(parts[0],parts[1]);
        txtQuestion.setText(parts[1]);
        //answer.setDescription();
       // answer.setCategory(parts[1]);
        return answer;
    }

    private void showQuestion (Question newQuestion){

        txtQuestion.setText(newQuestion.getStatement());
        btnA.setText(newQuestion.getAns1().getDescription());
        btnB.setText(newQuestion.getAns2().getDescription());
        btnC.setText(newQuestion.getAns3().getDescription());
        btnD.setText(newQuestion.getAns4().getDescription());
        btnE.setText(newQuestion.getAns5().getDescription());
        btnF.setText(newQuestion.getAns6().getDescription());

    }
    public void getQuestions (){
        String text = txt();
        String[]parts = text.split("_");
        txtQuestion.setText(parts[1]);
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


}

