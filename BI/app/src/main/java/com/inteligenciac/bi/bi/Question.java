package com.inteligenciac.bi.bi;

/**
 * Created by jguil on 19/03/2017.
 */

public class Question {

    private String statement;
    private Answer ans1;
    private Answer ans2;
    private Answer ans3;
    private Answer ans4;
    private Answer ans5;
    private Answer ans6 ;
    private String category;

    public Question(String statement, Answer ans1, Answer ans2, Answer ans3, Answer ans4, Answer ans5, Answer ans6, String category) {
        this.statement = statement;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.ans6 = ans6;
        this.category = category;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Answer getAns1() {
        return ans1;
    }

    public void setAns1(Answer ans1) {
        this.ans1 = ans1;
    }

    public Answer getAns2() {
        return ans2;
    }

    public void setAns2(Answer ans2) {
        this.ans2 = ans2;
    }

    public Answer getAns3() {
        return ans3;
    }

    public void setAns3(Answer ans3) {
        this.ans3 = ans3;
    }

    public Answer getAns4() {
        return ans4;
    }

    public void setAns4(Answer ans4) {
        this.ans4 = ans4;
    }

    public Answer getAns5() {
        return ans5;
    }

    public void setAns5(Answer ans5) {
        this.ans5 = ans5;
    }

    public Answer getAns6() {
        return ans6;
    }

    public void setAns6(Answer ans6) {
        this.ans6 = ans6;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
