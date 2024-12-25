package com.example.Digital.Art.Design.Rashu.EntityClasses;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "included_in")
@IdClass(Rashu_IncludedInId.class)
public class Rashu_IncludedIn implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "contest_code", referencedColumnName = "Contest_Code")
    private Rashu_Contest contest;

    @Id
    @ManyToOne
    @JoinColumn(name = "design_code", referencedColumnName = "Design_Code")
    private Rashu_Design design;

    @Column(name = "selection")
    private Integer selection;


    public Rashu_Contest getContest() {
        return contest;
    }

    public void setContest(Rashu_Contest contest) {
        this.contest = contest;
    }

    public Rashu_Design getDesign() {
        return design;
    }

    public void setDesign(Rashu_Design design) {
        this.design = design;
    }

    public Integer getSelection() {
        return selection;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }
}
