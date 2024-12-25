package com.example.Digital.Art.Design.Rashu.EntityClasses;

import jakarta.persistence.*;

@Entity
@Table(name = "works for")
@IdClass(Rashu_WorksForCompositeKey.class)
public class Rashu_WorksFor {
    @Id
    @ManyToOne
    @JoinColumn(name = "Designer_id", referencedColumnName = "designer_id")
    private Rashu_Designer designer;

    @Id
    @ManyToOne
    @JoinColumn(name = "Ctgr_Code", referencedColumnName = "ctgr_Code")
    private Rashu_Category category;

    // Other fields and methods as needed

    public Rashu_Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Rashu_Designer designer) {
        this.designer = designer;
    }

    public Rashu_Category getCategory() {
        return category;
    }

    public void setCategory(Rashu_Category category) {
        this.category = category;
    }
}
