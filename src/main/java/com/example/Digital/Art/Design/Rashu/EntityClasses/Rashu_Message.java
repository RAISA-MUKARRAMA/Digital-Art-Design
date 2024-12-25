package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="message")
public class Rashu_Message {
    @Id
    @Column(name="Date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_id")
    private Rashu_Admin admin;

    @ManyToOne
    @JoinColumn(name = "Designer_Id", referencedColumnName = "designer_id")
    private Rashu_Designer designer;

    @Column(name="Description")
    private String  description;

    public Rashu_Message() {
        this.date = LocalDateTime.now();
    }

    public Rashu_Admin getAdmin() {
        return admin;
    }

    public String getDescription() {
        return description;
    }

    public Rashu_Designer getDesigner() {
        return designer;
    }

    public LocalDateTime getDate() {
        return  date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAdmin(Rashu_Admin admin) {
        this.admin = admin;
    }

    public void setDesigner(Rashu_Designer designer) {
        this.designer = designer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
