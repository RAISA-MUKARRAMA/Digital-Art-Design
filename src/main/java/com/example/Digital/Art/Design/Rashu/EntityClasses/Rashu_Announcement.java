package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
@Entity
@Table(name = "announcement")
public class Rashu_Announcement {
    @Id
    @Column(name = "Anc_code")
    private Long anc_code;

    @Column(name = "Annc_Title")
    private String anc_Title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_id")
    private Rashu_Admin admin;


    // Constructors, getters, and setters

    public Rashu_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Rashu_Admin admin) {
        this.admin = admin;
    }

    public String getAnc_Title() {
        return anc_Title;
    }


    public Long getAnc_code() {
        return anc_code;
    }

    public String getDescription() {
        return description;
    }

    public void setAnc_Title(String anc_Title) {
        this.anc_Title = anc_Title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnc_code(Long anc_code) {
        this.anc_code = anc_code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
