package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "request to")
public class UserRequest {

    @Column(name = "Admin_Id")
    public Long adminId;

    @Column(name = "User_Id")
    public Long userId;

    @Column(name = "Description")
    public String description;

    @Id
    @Column(name = "Date")
    public LocalDateTime date;

    public UserRequest(){

    }
    public UserRequest(Long adminId, Long userId, String description, LocalDateTime date) {
        this.adminId = adminId;
        this.userId = userId;
        this.description = description;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
