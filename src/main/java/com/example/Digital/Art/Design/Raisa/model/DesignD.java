package com.example.Digital.Art.Design.Raisa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "design")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Exclude Hibernate proxy properties
public class DesignD {
    @Id
    private long Design_Code;
    @Column(name = "Design_Title")
    private String Design_Title;
    @Lob
    @Column(name="content", columnDefinition = "longblob")
    private byte[] content;
    @Column(name = "Description")
    private String Description;
    @Column(name = "rating")
    private long rating;
    @Column(name = "Designer_Id")
    private long Designer_Id;
    @Column(name = "Ctgr_Code")
    private int Ctgr_Code;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "included_in",
            joinColumns = @JoinColumn(name = "design_code"),
            inverseJoinColumns = @JoinColumn(name = "contest_code")
    )
    @MapKeyColumn(name = "selection",nullable = true)
    private Map<Integer, ContestD> contests = new HashMap<>();

    public DesignD() {
    }

    public DesignD(long design_Code, String design_Title, byte[] content, String description, long rating, long designer_Id, int ctgr_Code, Map<Integer, ContestD> contests) {
        Design_Code = design_Code;
        Design_Title = design_Title;
        this.content = content;
        Description = description;
        this.rating = rating;
        Designer_Id = designer_Id;
        Ctgr_Code = ctgr_Code;
        this.contests = contests;
    }

    public long getDesign_Code() {
        return Design_Code;
    }

    public void setDesign_Code(long design_Code) {
        Design_Code = design_Code;
    }

    public String getDesign_Title() {
        return Design_Title;
    }

    public void setDesign_Title(String design_Title) {
        Design_Title = design_Title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getDesigner_Id() {
        return Designer_Id;
    }

    public void setDesigner_Id(long designer_Id) {
        Designer_Id = designer_Id;
    }

    public int getCtgr_Code() {
        return Ctgr_Code;
    }

    public void setCtgr_Code(int ctgr_Code) {
        Ctgr_Code = ctgr_Code;
    }

    public Map<Integer, ContestD> getContests() {
        return contests;
    }

    public void setContests(Map<Integer, ContestD> contests) {
        this.contests = contests;
    }

    @Override
    public String toString() {
        return "Design{" +
                "Design_Code=" + Design_Code +
                ", Design_Title='" + Design_Title + '\'' +
                ", content=" + Arrays.toString(content) +
                ", Description='" + Description + '\'' +
                ", rating=" + rating +
                ", Designer_Id=" + Designer_Id +
                ", Ctgr_Code=" + Ctgr_Code +
                ", contests=" + contests +
                '}';
    }
}
