package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="request to")
public class Rashu_RequestTo {
    @Id
    @Column(name = "Date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_Id")
    private Rashu_Admin admin;

    @ManyToOne
    @JoinColumn(name = "User_Id", referencedColumnName = "user_id")
    private Rashu_User user;

    @Column(name="Description")
    private String description;

    public Rashu_RequestTo() {
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Rashu_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Rashu_Admin admin) {
        this.admin = admin;
    }

    public Rashu_User getUser() {
        return user;
    }

    public void setUser(Rashu_User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
