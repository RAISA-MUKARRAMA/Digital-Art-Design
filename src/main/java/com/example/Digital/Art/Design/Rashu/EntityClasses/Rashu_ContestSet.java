package com.example.Digital.Art.Design.Rashu.EntityClasses;
import java.time.LocalDate;
public class Rashu_ContestSet {
    private String contestTitle;
    private  LocalDate contestsDate;
    private LocalDate contestLDate;
    private String contestDescription;
    private Long maxSubmissions;
    private String confirmPassword;
    private Long id;

    private Long contestsUser;

    public Long getContestsUser() {
        return contestsUser;
    }


    public void setContestsUser(Long contestsUser) {
        this.contestsUser = contestsUser;
    }

    public String contestDescription() {
        return contestDescription;
    }

    public LocalDate contestLDate() {
        return contestLDate;
    }

    public LocalDate contestsDate() {
        return contestsDate;
    }

    public Long maxSubmissions() {
        return maxSubmissions;
    }

    public String contestTitle() {
        return contestTitle;
    }

    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    public void setContestLDate(LocalDate contestLDate) {
        this.contestLDate = contestLDate;
    }

    public void setContestsDate(LocalDate contestsDate) {
        this.contestsDate = contestsDate;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    public void setMaxSubmissions(Long maxSubmissions) {
        this.maxSubmissions = maxSubmissions;
    }

    public void setConfirmPassword(String confirmpassword) {
        this.confirmPassword = confirmpassword;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getConfirmpassword() {
        return confirmPassword;
    }
}
