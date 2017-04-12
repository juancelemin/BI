package com.inteligenciac.bi.bi;

import java.util.ArrayList;

/**
 * Created by jguil on 11/04/2017.
 */

public class ReportDataSet {

    private  String name [];
    private  ArrayList<Answer>data;

    public ReportDataSet(ArrayList<Answer> data) {
        this.data = data;
        name = new String[]{"Inconsciente", "Táctico", "Centrado", "Estratégico", "Omnipresente"};
    }

    public int[] numberdatachart(String category){
        int[] number = new int[5];
        for (Answer p:data) {
            if(category.equals("Personas")){
                if(p.getCategory().equals(name[0])){
                    number[0] = number[0] + 1;
                }else if(p.getCategory().equals(name[1])){
                    number[1] = number[1] + 1;
                }else if(p.getCategory().equals(name[2])){
                    number[2] = number[2] + 1;
                }else if(p.getCategory().equals(name[3])){
                    number[3] = number[3] + 1;
                }else if(p.getCategory().equals(name[4])){
                    number[4] = number[4] + 1;
                }

            }else{

            }
        }
        return number;
    }
    public String[] getName() {
        return name;
    }

    public ArrayList<Answer> getData() {
        return data;
    }

    public void setData(ArrayList<Answer> data) {
        this.data = data;
    }
}
