package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "category")
public class Rashu_Category {
    @Id
    @Column(name = "Ctgr_Code")
    private Integer ctgr_Code;

    @Column(name = "Ctgr_Title")
    private String ctgr_Title;


    public Rashu_Category() {
    }

    public Rashu_Category(Integer ctgr_Code, String ctgr_Title) {
        this.ctgr_Code = ctgr_Code;
        this.ctgr_Title = ctgr_Title;
    }

    public Integer getCtgr_Code() {
        return ctgr_Code;
    }

    public String getCtgr_Title() {
        return ctgr_Title;
    }

    public void setCtgr_Code(int ctgr_Code) {
        this.ctgr_Code = ctgr_Code;
    }

    public void setCtgr_Title(String ctgr_Title) {
        this.ctgr_Title = ctgr_Title;
    }

}
