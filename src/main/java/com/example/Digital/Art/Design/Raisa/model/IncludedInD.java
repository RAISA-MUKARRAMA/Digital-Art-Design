package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="included_in")
@IdClass(IncludedInIdD.class)
public class IncludedInD implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "contest_code", referencedColumnName = "Contest_Code")
    private ContestD contest;

    @Id
    @ManyToOne
    @JoinColumn(name = "design_code", referencedColumnName = "Design_Code")
    private DesignD design;


    @Column(name = "selection",nullable = true)
    private Integer selection;

    public IncludedInD() {
    }

    public IncludedInD(ContestD contest, DesignD design, Integer selection) {
        this.contest = contest;
        this.design = design;
        this.selection = selection;
    }

    public ContestD getContest() {
        return contest;
    }

    public void setContest(ContestD contest) {
        this.contest = contest;
    }

    public DesignD getDesign() {
        return design;
    }

    public void setDesign(DesignD design) {
        this.design = design;
    }

    public Integer getSelection() {
        return selection;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    @Override
    public String toString() {
        return "IncludedInD{" +
                "contest=" + contest +
                ", design=" + design +
                ", selection=" + selection +
                '}';
    }
}
