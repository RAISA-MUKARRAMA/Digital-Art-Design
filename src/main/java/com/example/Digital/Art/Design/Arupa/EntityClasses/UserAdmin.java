package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "admin")
public class UserAdmin {
    @Id
    @Column(name = "Admin_id") // Map to "Designer_id" column in the database
    private Long id;

    @Column(name = "Admin_name") // Map to "Designer_Name" column in the database
    private String name;

    @Column(name = "date_of_birth") // Map to "Date of Birth" column in the database
    private LocalDate dob;

    @Column(name = "Bio") // Map to "Bio" column in the database
    private String bio;

    @Column(name = "Email_Address") // Map to "Email_Address" column in the database
    private String email;

    @Column(name = "Address") // Map to "Address" column in the database
    private String adds;

    @Column(name = "Contact_No") // Map to "Contact_No" column in the database
    private String contact;

    @Column(name = "Password") // Map to "Password" column in the database
    private String pass;

    public UserAdmin() {
    }

    public UserAdmin(
            String name,
            String email,
            String contact,
            String pass) {

        this.name = name;
        this.email = email;
        this.contact = contact;
        this.pass = pass;
        adds = "";
        bio = "";
        dob = LocalDate.of(0001, 1, 1);
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getBio() {
        return bio;
    }

    public String getAdds() {
        return adds;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }


}
