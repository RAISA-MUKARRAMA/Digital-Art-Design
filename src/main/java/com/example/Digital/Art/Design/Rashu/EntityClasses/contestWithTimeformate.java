package com.example.Digital.Art.Design.Rashu.EntityClasses;

public class contestWithTimeformate {
        private Rashu_Contest contest;
        private String Last;
        private String S;

    public contestWithTimeformate(Rashu_Contest contest,String last,String start) {
        this.contest = contest; Last = last;S = start;
    }

    public Rashu_Contest getContest() {
        return contest;
    }

    public void setContest(Rashu_Contest contest) {
        this.contest = contest;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getS() {
        return S;
    }

    public void setS(String start) {
        S = start;
    }
}
