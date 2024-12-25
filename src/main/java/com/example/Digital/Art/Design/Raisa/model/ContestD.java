package com.example.Digital.Art.Design.Raisa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contest")
public class ContestD {
    @Id
    @Column(name="Contest_Code")
    private long Contest_Code;
    @Column(name = "Contest_Title")
    private String Contest_Title;
    @Column(name = "Description")
    private String Description;
    @Column(name = "starting_date")
    private LocalDateTime Starting_Date;
    @Column(name = "last_date_of_sub")
    private LocalDateTime Last_Date_of_Sub;
    @Column(name = "max_sub")
    private long max_sub;
    @Column(name = "Admin_Id")
    private long Admin_Id;

    @Column(name = "User ID")
    private long User_ID;

    @ManyToMany(mappedBy = "contests")
    private Set<com.example.Digital.Art.Design.Raisa.model.DesignD> designs = new HashSet<>();

    public ContestD() {
    }

    public ContestD(long contest_Code, String contest_Title, String description,
                    LocalDateTime starting_Date, LocalDateTime last_Date_of_Sub, long max_sub,
                    long admin_Id, long user_ID, Set<DesignD> designs) {
        Contest_Code = contest_Code;
        Contest_Title = contest_Title;
        Description = description;
        Starting_Date = starting_Date;
        Last_Date_of_Sub = last_Date_of_Sub;
        this.max_sub = max_sub;
        Admin_Id = admin_Id;
        User_ID = user_ID;
        this.designs = designs;
    }


    public long getContest_Code() {
        return Contest_Code;
    }

    public void setContest_Code(long contest_Code) {
        Contest_Code = contest_Code;
    }

    public String getContest_Title() {
        return Contest_Title;
    }

    public void setContest_Title(String contest_Title) {
        Contest_Title = contest_Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getStarting_Date() {
        return Starting_Date;
    }

    public void setStarting_Date(LocalDateTime starting_Date) {
        Starting_Date = starting_Date;
    }

    public LocalDateTime getLast_Date_of_Sub() {
        return Last_Date_of_Sub;
    }

    public void setLast_Date_of_Sub(LocalDateTime last_Date_of_Sub) {
        Last_Date_of_Sub = last_Date_of_Sub;
    }

    public long getMax_sub() {
        return max_sub;
    }

    public void setMax_sub(long max_sub) {
        this.max_sub = max_sub;
    }

    public long getAdmin_Id() {
        return Admin_Id;
    }

    public void setAdmin_Id(long admin_Id) {
        Admin_Id = admin_Id;
    }

    public Set<com.example.Digital.Art.Design.Raisa.model.DesignD> getDesigns() {
        return designs;
    }

    public void setDesigns(Set<com.example.Digital.Art.Design.Raisa.model.DesignD> designs) {
        this.designs = designs;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_ID) {
        User_ID = user_ID;
    }

    @Override
    public String toString() {
        return "ContestD{" +
                "Contest_Code=" + Contest_Code +
                ", Contest_Title='" + Contest_Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Starting_Date=" + Starting_Date +
                ", Last_Date_of_Sub=" + Last_Date_of_Sub +
                ", max_sub=" + max_sub +
                ", Admin_Id=" + Admin_Id +
                ", User_ID=" + User_ID +
                ", designs=" + designs +
                '}';
    }
}
