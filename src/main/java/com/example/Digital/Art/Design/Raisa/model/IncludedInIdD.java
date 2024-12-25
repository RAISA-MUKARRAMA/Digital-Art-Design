package com.example.Digital.Art.Design.Raisa.model;

import java.io.Serializable;

public class IncludedInIdD implements Serializable {
    private Long contest; // Corresponds to the Contest foreign key
    private Long design;  // Corresponds to the Design foreign key

    public IncludedInIdD() {
    }

    public IncludedInIdD(Long contest, Long design) {
        this.contest = contest;
        this.design = design;
    }

    public Long getContest() {
        return contest;
    }

    public void setContest(Long contest) {
        this.contest = contest;
    }

    public Long getDesign() {
        return design;
    }

    public void setDesign(Long design) {
        this.design = design;
    }

    @Override
    public String toString() {
        return "IncludedInIdD{" +
                "contest=" + contest +
                ", design=" + design +
                '}';
    }
}
