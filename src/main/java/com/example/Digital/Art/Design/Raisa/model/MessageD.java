package com.example.Digital.Art.Design.Raisa.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="message")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Add this annotation
public class MessageD {
    @Id
    @Column(name="Date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "Admin_Id")
    private AdminD admin;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Designer_Id", referencedColumnName = "designer_id")
    private DesignerD designer;

    @Column(name="Description")
    private String  description;

    public MessageD() {
        this.date = LocalDateTime.now();
    }

    public AdminD getAdmin() {
        return admin;
    }

    public String getDescription() {
        return description;
    }

    public DesignerD getDesigner() {
        return designer;
    }

    public LocalDateTime getDate() {
        return  date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAdmin(AdminD admin) {
        this.admin = admin;
    }

    public void setDesigner(DesignerD designer) {
        this.designer = designer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}