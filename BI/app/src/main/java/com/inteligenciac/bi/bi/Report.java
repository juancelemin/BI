package com.inteligenciac.bi.bi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Report extends AppCompatActivity implements View.OnClickListener {

    final static String[] name = new String[]{"Inconsciente","Táctico","Centrado","Estratégico","Omnipresente"};
    PieChart pie;
    RadioButton people;
    RadioButton process;
    RadioButton technology;
    ArrayList<Answer> data = new ArrayList<>();

    ImageButton exit;

    float peopleA[] = new float[5];
    float processA[] = new float[5];
    float technologyA[] = new float[5];

    int totalPeople = 0 ;
    int totalProcess = 0 ;
    int totalTecno = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        pie = (PieChart)findViewById(R.id.chart);

        people =(RadioButton)findViewById(R.id.rbPeople);
        process =(RadioButton)findViewById(R.id.rbProcess);
        technology =(RadioButton)findViewById(R.id.rbTechnology);

        people.setOnClickListener(this);
        process.setOnClickListener(this);
        technology.setOnClickListener(this);

        exit = (ImageButton)findViewById(R.id.btnExit);

        exit.setOnClickListener(this);



        getData();
        setUpPieChart("Personas");
    }

    public void getData(){
        Bundle extra = getIntent().getExtras();
        data =  extra.getParcelableArrayList("Personas");
        //ReportDataSet r = new ReportDataSet(p);
        //int [] s = r.numberdatachart("Personas");
        //Toast.makeText(Report.this,s[0]+s[1]+s[2]+s[3]+s[4]+"",Toast.LENGTH_SHORT).show();
        numberdatachart("Personas");

    }


    public void numberdatachart(String category){
        float[] number = new float[5];
        float[] numberProc = new float[5];
        float[] numberTecno = new float[5];
        for (Answer p:data) {
            if(category.equals("Personas")){
                //Toast.makeText(Report.this,p.getCategory()+" "+name[0],Toast.LENGTH_SHORT).show();
                if(p.getCategory().contains(name[0])){
                    //Toast.makeText(Report.this,p.getCategory()+""+name[0],Toast.LENGTH_LONG).show();
                    number[0] = number[0] + 1;
                }else if(p.getCategory().contains(name[1])){
                    number[1] = number[1] + 1;
                }else if(p.getCategory().contains(name[2])){
                    number[2] = number[2] + 1;
                }else if(p.getCategory().contains(name[3])){
                    number[3] = number[3] + 1;
                }else if(p.getCategory().contains(name[4])){
                    number[4] = number[4] + 1;
                }
            }else if(category.equals("Procesos")){

                if(p.getCategory().contains(name[0])){
                    numberProc[0] = number[0] + 1;
                }else if(p.getCategory().contains(name[1])){
                    numberProc[1] = number[1] + 1;
                }else if(p.getCategory().contains(name[2])){
                    numberProc[2] = number[2] + 1;
                }else if(p.getCategory().contains(name[3])){
                    numberProc[3] = number[3] + 1;
                }else if(p.getCategory().contains(name[4])){
                    numberProc[4] = number[4] + 1;
                }
            }
            else if(category.equals("Tecnologias")){

                if(p.getCategory().contains(name[0])){
                    numberTecno[0] = number[0] + 1;
                }else if(p.getCategory().contains(name[1])){
                    numberTecno[1] = number[1] + 1;
                }else if(p.getCategory().contains(name[2])){
                    numberTecno[2] = number[2] + 1;
                }else if(p.getCategory().contains(name[3])){
                    numberTecno[3] = number[3] + 1;
                }else if(p.getCategory().contains(name[4])){
                    numberTecno[4] = number[4] + 1;
                }
            }
        }
        Toast.makeText(Report.this,number[0]+""+number[1]+""+number[2]+""+number[3]+""+number[4],Toast.LENGTH_SHORT).show();
        peopleA = number;

        processA = numberProc;

        technologyA = numberTecno;
        dataAnality();
    }
    private void dataAnality (){

        for (int i = 0; i< processA.length; i++){
            totalPeople += peopleA[i];
            totalProcess += processA[i];
            totalTecno += technologyA[i];
        }
        for (int i = 0; i< peopleA.length; i++){
            processA[i] = (processA[i]*100)/totalProcess;
            peopleA[i] = (peopleA[i]*100)/totalPeople;
            technologyA[i] = (technologyA[i]*100)/totalTecno;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbPeople:
                getPerson();
                break;
            case R.id.rbProcess:
                getProcess();
                break;
            case R.id.rbTechnology:
               // getTechnology();
                break;
            case R.id.btnExit:
                finish();
                break;
        }

    }

    private void getTechnology() {
        setValues(technologyA,name);
        setUpPieChart("Tecnologia");
    }


    public void getProcess (){
        float val[] = {30.4f,10.0f,8.9f,20.6f,13.7f};
        String nom[] = {"Oficina","Mercadeo","Omnipresente","casa","puerta"};
        setValues(val,nom);
        setUpPieChart("Procesos");
    }

    public void getPerson(){

        setValues(peopleA,name);
        detail(0);
        setUpPieChart("Personas");
    }


    public void setValues (float[] values,String[]topics){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i< values.length; i++){
            pieEntries.add(new PieEntry(values[i],topics[i]));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Nivel BI");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.WHITE);
        pie.setData(data);
        //detail();
        pie.invalidate();
    }

    private void setUpPieChart(String name) {

        Legend legend = pie.getLegend();
        pie.getLegend().setTextSize(11);
        pie.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        pie.getLegend().setForm(Legend.LegendForm.CIRCLE);
        Description description = new Description();
        description.setText("Answers");
        pie.setDescription(description);
        pie.setHoleRadius(30f);
        pie.setTransparentCircleAlpha(0);
        pie.setCenterText(name);
        pie.animateY(3000);
        pie.invalidate();

    }

    public void detail (final int topic){


        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            int p =0;
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) h.getX();
                if(topic == 0){
                    p = (int)(totalPeople*peopleA[index])/100;
                    Toast.makeText(Report.this,peopleA[index]+" % "+"son : "+p+" respuestas",Toast.LENGTH_LONG).show();
                }else if(topic == 1){
                    p = (int)(totalProcess*processA[index])/100;
                    Toast.makeText(Report.this,processA[index]+" % "+"son : "+p+" respuestas",Toast.LENGTH_LONG).show();

                } else if(topic == 2){
                    p = (int)(totalTecno*technologyA[index])/100;
                    Toast.makeText(Report.this,technologyA[index]+" % "+"son : "+p+" respuestas",Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onNothingSelected() {

            }
        });

    }
}
