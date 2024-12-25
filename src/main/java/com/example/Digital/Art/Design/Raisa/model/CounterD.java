package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "counter")
public class CounterD {
    @Id
    private long count_number;
    @Column(name = "Designer_Id_curr")
    private long Designer_Id_curr;
    @Column(name = "User_Id_curr")
    private long User_Id_curr;
    @Column(name = "Contest_Code_curr")
    private long Contest_Code_curr;
    @Column(name = "Design_Code_curr")
    private long Design_Code_curr;
    @Column(name = "annc_code_curr")
    private long annc_code_curr;
    @Column(name = "Order_Code_curr")
    private long Order_Code_curr;

    public CounterD() {
    }

    public CounterD(long count_number, long designer_Id_curr, long user_Id_curr, long contest_Code_curr, long design_Code_curr, long annc_code_curr, long order_Code_curr) {
        this.count_number = count_number;
        Designer_Id_curr = designer_Id_curr;
        User_Id_curr = user_Id_curr;
        Contest_Code_curr = contest_Code_curr;
        Design_Code_curr = design_Code_curr;
        this.annc_code_curr = annc_code_curr;
        Order_Code_curr = order_Code_curr;
    }

    public long getCount_number() {
        return count_number;
    }

    public void setCount_number(long count_number) {
        this.count_number = count_number;
    }

    public long getDesigner_Id_curr() {
        return Designer_Id_curr;
    }

    public void setDesigner_Id_curr(long designer_Id_curr) {
        Designer_Id_curr = designer_Id_curr;
    }

    public long getUser_Id_curr() {
        return User_Id_curr;
    }

    public void setUser_Id_curr(long user_Id_curr) {
        User_Id_curr = user_Id_curr;
    }

    public long getContest_Code_curr() {
        return Contest_Code_curr;
    }

    public void setContest_Code_curr(long contest_Code_curr) {
        Contest_Code_curr = contest_Code_curr;
    }

    public long getDesign_Code_curr() {
        return Design_Code_curr;
    }

    public void setDesign_Code_curr(long design_Code_curr) {
        Design_Code_curr = design_Code_curr;
    }

    public long getAnnc_code_curr() {
        return annc_code_curr;
    }

    public void setAnnc_code_curr(long annc_code_curr) {
        this.annc_code_curr = annc_code_curr;
    }

    public long getOrder_Code_curr() {
        return Order_Code_curr;
    }

    public void setOrder_Code_curr(long order_Code_curr) {
        Order_Code_curr = order_Code_curr;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "count_number=" + count_number +
                ", Designer_Id_curr=" + Designer_Id_curr +
                ", User_Id_curr=" + User_Id_curr +
                ", Contest_Code_curr=" + Contest_Code_curr +
                ", Design_Code_curr=" + Design_Code_curr +
                ", annc_code_curr=" + annc_code_curr +
                ", Order_Code_curr=" + Order_Code_curr +
                '}';
    }
}
