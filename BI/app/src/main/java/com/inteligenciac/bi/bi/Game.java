package com.inteligenciac.bi.bi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    Button btnA, btnB, btnC, btnD,btnE, btnF;
    ImageButton btnListA, btnListB,btnListC,btnListD,btnListE,btnListF,btnListen;
    TextView txtQuestion;
    ArrayList<Question> question  = new ArrayList<>();
    Question currentQuestion;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tts = new TextToSpeech(this,this);

        btnA = (Button)findViewById(R.id.btnA);
        btnB = (Button)findViewById(R.id.btnB);
        btnC = (Button)findViewById(R.id.btnC);
        btnD = (Button)findViewById(R.id.btnD);
        btnE = (Button)findViewById(R.id.btnE);
        btnF = (Button)findViewById(R.id.btnF);

        btnListA = (ImageButton)findViewById(R.id.btnLisA);
        btnListB = (ImageButton)findViewById(R.id.btnLisB);
        btnListC = (ImageButton)findViewById(R.id.btnLisC);
        btnListD = (ImageButton)findViewById(R.id.btnLisD);
        btnListE = (ImageButton)findViewById(R.id.btnLisE);
        btnListF = (ImageButton)findViewById(R.id.btnLisF);
        btnListen = (ImageButton)findViewById(R.id.btnListen);


        txtQuestion = (TextView)findViewById(R.id.txtQuestion);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);


        btnListA.setOnClickListener(this);
        btnListB.setOnClickListener(this);
        btnListC.setOnClickListener(this);
        btnListD.setOnClickListener(this);
        btnListE.setOnClickListener(this);
        btnListF.setOnClickListener(this);
        btnListen.setOnClickListener(this);



        getQuestions();


    }

    @Override
    public void onClick(View v) {
        int color = Color.parseColor("#9b00e2");
        View contenedor = v.getRootView();
        contenedor.setBackgroundColor(color);
        switch (v.getId()){
            case R.id.btnListen:
                hablar(currentQuestion.getStatement());
                break;

            case R.id.btnA:
                Intent intent = new Intent(Game.this,Report.class);
                startActivity(intent);

                break;
            case R.id.btnB:

                break;
            case R.id.btnC:

                break;
            case R.id.btnD:

                break;
            case R.id.btnLisA:
                hablar(currentQuestion.getAns1().getDescription());
                break;
            case R.id.btnLisB:
                hablar(currentQuestion.getAns2().getDescription());
                break;
            case R.id.btnLisC:
                hablar(currentQuestion.getAns3().getDescription());
                break;
            case R.id.btnLisD:
                hablar(currentQuestion.getAns4().getDescription());
                break;
            case R.id.btnLisE:
                hablar(currentQuestion.getAns5().getDescription());
                break;
            case R.id.btnLisF:
                hablar(currentQuestion.getAns6().getDescription());
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
    public void hablar (String p){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(p, TextToSpeech.QUEUE_FLUSH, null, null);

            //Toast.makeText(this, "5.0", Toast.LENGTH_SHORT).show();
        }
        else {
            tts.speak(p, TextToSpeech.QUEUE_ADD, null);
            //Toast.makeText(this, "4.4.4", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            Locale P= new Locale("ES");
            tts.setLanguage(P);
            tts.setPitch(1);
        }
    }

}

