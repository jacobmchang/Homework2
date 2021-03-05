package com.example.homework2;

import java.util.ArrayList;

public class Timer {
    private ArrayList<String> timeList;
    private int sec;
    private int min;
    private int hr;

    public Timer()
    {
        timeList = new ArrayList<String>();
        sec = 0;
        min = 0;
        hr = 0;
    }

    public ArrayList<String> getTimeList() {
        return timeList;
    }

    public int getSec() {
        return sec;
    }

    public int getMin() {
        return min;
    }

    public int getHr() {
        return hr;
    }

    public void calc()
    {
        sec++;
        if (sec == 60) {
            sec = 0;
            min++;
        }
        if (min == 60) {
            hr++;
        }
    }

    public ArrayList<String> addTime(String time)
    {
        timeList.add(time);
        return timeList;
    }

    public void reset()
    {
        sec = 0;
        min = 0;
        hr = 0;
        timeList = new ArrayList<String>();
    }

    public void setTime(int hr, int min, int sec) {
        this.hr = hr;
        this.min = min;
        this.sec = sec;
    }

    public void setTimeList(ArrayList<String> tl) {
        this.timeList = tl;
    }


}
