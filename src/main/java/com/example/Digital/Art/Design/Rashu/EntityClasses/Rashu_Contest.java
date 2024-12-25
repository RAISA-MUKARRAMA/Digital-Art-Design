package com.example.Digital.Art.Design.Rashu.EntityClasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

@Entity
@Table(name = "contest")
public class Rashu_Contest {
    @Id
    @Column(name = "Contest_code")
    private Long cnt_code;

    @Column(name = "Contest_Title")
    private String cnt_Title;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Starting_Date")
    private LocalDate Start;

    @Column(name = "Last_Date_of_Sub")
    private LocalDate Last;

    @ManyToOne // Indicates a many-to-one relationship
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_Id")
    private Rashu_Admin admin; // Represents the related Admin entity

    @ManyToOne // Indicates a many-to-one relationship
    @JoinColumn(name = "User ID", referencedColumnName = "user_id")
    private Rashu_User user; // Represents the related Admin entity

    @Column(name = "Max_Sub")
    private Long MxS;

    public Rashu_User getUser() {
        return user;
    }

    public void setUser(Rashu_User user) {
        this.user = user;
    }

    public Long getCnt_code() {
        return cnt_code;
    }

    public void setCnt_code(Long cnt_code) {
        this.cnt_code = cnt_code;
    }

    public String getCnt_Title() {
        return cnt_Title;
    }

    public void setCnt_Title(String cnt_Title) {
        this.cnt_Title = cnt_Title;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDate getLast() {
        return Last;
    }

    public void setLast(LocalDate lastdate) {
        Last = lastdate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getStart() {
        return Start;
    }

    public void setStart(LocalDate startdate) {
        Start = startdate;
    }

    public Rashu_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Rashu_Admin admin) {
        this.admin = admin;
    }

    public Long getMxS() {
        return MxS;
    }

    public void setMxS(Long mxS) {
        MxS = mxS;
    }
}
