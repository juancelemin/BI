package com.inteligenciac.bi.bi;

import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Report extends AppCompatActivity implements View.OnClickListener {

    float valores[] = {2.4f,5.0f,8.5f,3.6f,7,6.1f,6,7,15.8f};
    String nombre[] = {"juan","carlos","andres","ricardo","laura","maria","andrea","luis","camila"};
    PieChart pie;
    RadioButton people;
    RadioButton process;
    RadioButton other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        pie = (PieChart)findViewById(R.id.chart);

        people =(RadioButton)findViewById(R.id.rbPeople);
        process =(RadioButton)findViewById(R.id.rbProcess);
        other =(RadioButton)findViewById(R.id.rbOther);

        people.setOnClickListener(this);
        process.setOnClickListener(this);
        other.setOnClickListener(this);


        setUpPieChart();
        setUpPieChart();


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
            case R.id.rbOther:

                break;
        }

    }

    public void getProcess (){
        float val[] = {30.4f,10.0f,8.9f,20.6f,13.7f};
        String nom[] = {"Oficina","Mercadeo","Omnipresente","casa","puerta"};
        setValues(val,nom);
        setUpPieChart();
    }
    public void getPerson(){

        setValues(valores,nombre);
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

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) h.getX();
                Toast.makeText(Report.this,nombre[index]+"",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {

            }
        });

    }


}
