package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class UserD {
    @Id
    private Long user_id;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "bio")
    private String bio;
    @Column(name = "contact_no")
    private String contact_no;
    @Column(name = "email_address")
    private String email_address;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birth")
    private LocalDate date_of_birth;

    public UserD() {
    }

    public UserD(Long user_id, String password, String user_name, String bio, String contact_no, String email_address, String address, LocalDate date_of_birth) {
        this.user_id = user_id;
        this.password = password;
        this.user_name = user_name;
        this.bio = bio;
        this.contact_no = contact_no;
        this.email_address = email_address;
        this.address = address;
        this.date_of_birth = date_of_birth;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "UserD{" +
                "user_id=" + user_id +
                ", password='" + password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", bio='" + bio + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", email_address='" + email_address + '\'' +
                ", address='" + address + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
