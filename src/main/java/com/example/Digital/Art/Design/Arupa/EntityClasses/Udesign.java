package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "design")
public class Udesign {
    @Id
    @Column(name = "Design_Code")
    public Long design_code;
    @Column(name = "Design_Title")
    public String dTitle;
    @Column(name = "content")
    public byte[] content;
    @Column(name = "Description")
    public String description;
    @Column(name = "rating")
    public Long rating;
    @Column(name = "Designer_Id")
    public Long designer_id;
    @Column(name = "Ctgr_Code")
    public Integer ctgr_code;

    public Udesign(){}
    public Udesign(
            Long code,
            String title,
            String description,
            Long designer_id,
            Integer ctgr_code)
    {

        this.design_code = code;
        this.dTitle = title;
        this.description = description;
        this.designer_id = designer_id;
        this.ctgr_code = ctgr_code;
    }
    public Long getDesign_code() {
        return design_code;
    }

    public void setDesign_code(Long design_code) {
        this.design_code = design_code;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(Long designer_id) {
        this.designer_id = designer_id;
    }

    public Integer getCtgr_code() {
        return ctgr_code;
    }

    public void setCtgr_code(Integer ctgr_code) {
        this.ctgr_code = ctgr_code;
    }

    @ManyToMany(mappedBy = "likedDesigns")
    public Set<UserProfile> usersWhoLiked = new HashSet<>();

    public void setUsersWhoLiked(Set<UserProfile> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public Set<UserProfile> getUsers(){
        return usersWhoLiked;
    }

    @ManyToMany
    @JoinTable(name = "included_in", joinColumns = @JoinColumn(name = "design_code"),
    inverseJoinColumns = @JoinColumn(name = "contest_code"))
    public Set<UserContest> iContests = new HashSet<>();

    public Set<UserContest> getiContests() {
        return iContests;
    }
}
