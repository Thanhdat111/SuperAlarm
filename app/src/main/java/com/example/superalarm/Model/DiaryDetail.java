package com.example.superalarm.Model;

public class DiaryDetail {
    String time;
    String result;

    public DiaryDetail(){}

    public DiaryDetail(String time, String result) {
        this.time = time;
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
