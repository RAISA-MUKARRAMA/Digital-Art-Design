package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "announcement")
public class AnnouncementD {
    @Id
    @Column(name="Anc_Code ")
    private Long Anc_Code;
    @Column(name = "Annc_Title")
    private String Annc_Title;
    @Column(name = "Description")
    private String Description;
    @Column(name = "Date")
    private LocalDateTime Date;
    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_Id")
    private AdminD admin;

    public AnnouncementD() {
    }

    public AnnouncementD(Long anc_Code, String annc_Title, String description, LocalDateTime date, AdminD admin) {
        Anc_Code = anc_Code;
        Annc_Title = annc_Title;
        Description = description;
        Date = date;
        this.admin = admin;
    }

    public Long getAnc_Code() {
        return Anc_Code;
    }

    public void setAnc_Code(Long anc_Code) {
        Anc_Code = anc_Code;
    }

    public String getAnnc_Title() {
        return Annc_Title;
    }

    public void setAnnc_Title(String annc_Title) {
        Annc_Title = annc_Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public AdminD getAdmin() {
        return admin;
    }

    public void setAdmin(AdminD admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "AnnouncementD{" +
                "Anc_Code=" + Anc_Code +
                ", Annc_Title='" + Annc_Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Date=" + Date +
                ", admin=" + admin +
                '}';
    }
}
