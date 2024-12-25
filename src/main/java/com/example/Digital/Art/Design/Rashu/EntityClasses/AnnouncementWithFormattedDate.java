package com.example.Digital.Art.Design.Rashu.EntityClasses;

import java.time.LocalDateTime;
public class AnnouncementWithFormattedDate {
    private Rashu_Announcement annc;
    private String formattedDate;

    public AnnouncementWithFormattedDate(Rashu_Announcement announcement, String formattedDate) {
        this.annc = announcement;
        this.formattedDate = formattedDate;
    }


    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Rashu_Announcement getAnnc() {
        return annc;
    }

    public void setAnnc(Rashu_Announcement annc) {
        this.annc = annc;
    }
}
