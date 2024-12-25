package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contest")
public class UserContest {
    @Id
    @Column(name = "Contest_Code")
    public Long contestId;

    @Column(name = "Contest_Title")
    public String title;

    @Column(name = "Description")
    public String description;

    @Column(name = "starting_date")
    public LocalDateTime start;

    @Column(name = "last_date_of_sub")
    public LocalDateTime last;

    @Column(name = "max_sub")
    public Long maxSub;

    @Column(name = "Admin_Id")
    public Long adminId;

    @Column(name = "User ID")
    public Long userId;

    public UserContest(){

    }
    public UserContest(Long contestId, String title,
                       String description, LocalDateTime start,
                       LocalDateTime last, Long maxSub, Long adminId,
                       Long userId) {
        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.start = start;
        this.last = last;
        this.maxSub = maxSub;
        this.adminId = adminId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getLast() {
        return last;
    }

    public void setLast(LocalDateTime last) {
        this.last = last;
    }

    public Long getMaxSub() {
        return maxSub;
    }

    public void setMaxSub(Long maxSub) {
        this.maxSub = maxSub;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ManyToMany(mappedBy = "iContests")
    public Set<Udesign> iDesigns = new HashSet<>();

    public Set<Udesign> getiDesigns() {
        return iDesigns;
    }
}
