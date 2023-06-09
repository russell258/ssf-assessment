package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;

public class Captcha implements Serializable{
    private int first;
    private int second;
    private char op;
    private int answer;

    public int getFirst() {
        return first;
    }
    public void setFirst(int first) {
        this.first = first;
    }
    public int getSecond() {
        return second;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public char getOp() {
        return op;
    }
    public void setOp(char op) {
        this.op = op;
    }
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    
}
