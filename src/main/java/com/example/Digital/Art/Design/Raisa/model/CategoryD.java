package com.example.Digital.Art.Design.Raisa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryD {

    @Id
    @Column(name="Ctgr_Code ")
    private int Ctgr_code;
    @Column(name = "Ctgr_Title")
    private String ctgr_Title;

    @ManyToMany(mappedBy = "categories")
    private Set<com.example.Digital.Art.Design.Raisa.model.DesignerD> designers = new HashSet<>();

    public CategoryD() {
    }

    public CategoryD(int ctgr_code, String ctgr_Title, Set<com.example.Digital.Art.Design.Raisa.model.DesignerD> designerc) {
        Ctgr_code = ctgr_code;
        this.ctgr_Title = ctgr_Title;
        this.designers = designerc;
    }

    public int getCtgr_code() {
        return Ctgr_code;
    }

    public void setCtgr_code(int ctgr_code) {
        Ctgr_code = ctgr_code;
    }

    public String getCtgr_Title() {
        return ctgr_Title;
    }

    public void setCtgr_Title(String ctgr_Title) {
        this.ctgr_Title = ctgr_Title;
    }

    public Set<com.example.Digital.Art.Design.Raisa.model.DesignerD> getDesignerc() {
        return designers;
    }

    public void setDesignerc(Set<com.example.Digital.Art.Design.Raisa.model.DesignerD> designerc) {
        this.designers = designerc;
    }

//    @Override
//    public String toString() {
//        return "CategoryD{" +
//                "Ctgr_code=" + Ctgr_code +
//                ", ctgr_Title='" + ctgr_Title + '\'' +
//                ", designerc=" + designers +
//                '}';
//    }

    @Override
    public String toString() {
        return "CategoryD{" +
                "Ctgr_code=" + Ctgr_code +
                ", ctgr_Title='" + ctgr_Title + '\'' +
                '}';
    }
}
