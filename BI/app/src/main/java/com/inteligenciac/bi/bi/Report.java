package com.inteligenciac.bi.bi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    float peopleA[] = new float[5];
    float processA[] = new float[5];
    float technologyA[] = new float[5];

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


        setUpPieChart();
        getData();
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
                    Toast.makeText(Report.this,p.getCategory()+""+name[0],Toast.LENGTH_LONG).show();
                    number[0] = number[0] + 1;
                }else if(p.getCategory()==(name[1])){
                    number[1] = number[1] + 1;
                }else if(p.getCategory()==(name[2])){
                    number[2] = number[2] + 1;
                }else if(p.getCategory().contains(name[3])){
                    number[3] = number[3] + 1;
                }else if(p.getCategory()==(name[4])){
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
    }
    private void dataAnality (float[] values){
        int total = 0 ;
        for (int i = 0; i< values.length; i++){
            total += values[i];
        }
        for (int i = 0; i< values.length; i++){
            values[i] = (values[i]*100)/total;
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
        }

    }

    private void getTechnology() {
        setValues(technologyA,name);
        setUpPieChart();
    }


    public void getProcess (){
        float val[] = {30.4f,10.0f,8.9f,20.6f,13.7f};
        String nom[] = {"Oficina","Mercadeo","Omnipresente","casa","puerta"};
        setValues(val,nom);
        setUpPieChart();
    }

    public void getPerson(){

        setValues(peopleA,name);
        setUpPieChart();
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
        detail();
        pie.invalidate();
    }

    private void setUpPieChart() {

        Legend legend = pie.getLegend();
        pie.getLegend().setTextSize(12);
        pie.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        pie.getLegend().setForm(Legend.LegendForm.CIRCLE);
        Description description = new Description();
        description.setText("Question/Answers");
        pie.setDescription(description);
        pie.setHoleRadius(30f);
        pie.setTransparentCircleAlpha(0);
        pie.setCenterText("Procesos");
        pie.animateY(3000);
        pie.invalidate();

    }

    public void detail (){

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) h.getX();
                //Toast.makeText(Report.this,nombre[index]+"",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {

            }
        });

    }
}
