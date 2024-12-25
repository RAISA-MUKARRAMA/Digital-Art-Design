package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class UserCategory {
    @Id
    @Column(name = "Ctgr_Code")// Map to "Designer_id" column in the database
    public Integer cId;

    @Column(name = "Ctgr_Title")
    public String cName;

    public UserCategory(){

    }
    public UserCategory(Integer cId, String cName){
        this.cId = cId;
        this.cName = cName;
    }
    public Integer getId() {
        return cId;
    }
    public String getName(){
        return cName;
    }
    public void setId(Integer cId){
        this.cId = cId;
    }
    public void setName(String cName){
        this.cName = cName;
    }

    @ManyToMany(mappedBy = "wCategories")
    public Set<UserDesigner> wDesigners = new HashSet<>();
    public Set<UserDesigner> getDesigners(){
        return wDesigners;
    }
}
