package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "designer")
public class UserDesigner {
    @Id
    @Column(name = "designer_id")
    public Long id;

    @Column(name = "designer_name") // Map to "Designer_Name" column in the database
    public String name;

    @Column(name = "date_of_birth") // Map to "Date of Birth" column in the database
    public LocalDate dob;

    @Column(name = "bio") // Map to "Bio" column in the database
    public String bio;

    @Column(name = "email_address") // Map to "Email_Address" column in the database
    public String email;

    @Column(name = "address") // Map to "Address" column in the database
    public String adds;

    @Column(name = "contact_no") // Map to "Contact_No" column in the database
    public String contact;

    @Column(name = "password") // Map to "Password" column in the database
    public String pass;

    @Lob
    @Column(name="profile_photo", columnDefinition = "longblob")
    private byte[] profilePhoto;

    public UserDesigner(){}

    public UserDesigner(
            String name,
            String email,
            String contact,
            String pass)
    {

        this.name=name;
        this.email=email;
        this.contact=contact;
        this.pass=pass;
        adds="";
        bio="";
        dob=LocalDate.of(0001,1,1);
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

    public void setId(Long id)
    {
        this.id=id;
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

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @ManyToMany
    @JoinTable(name="works for", joinColumns = @JoinColumn(name="Designer_Id"),
    inverseJoinColumns = @JoinColumn(name="Ctgr_Code"))

    public Set<UserCategory> wCategories = new HashSet<>();
    public Set<UserCategory> getwCategories() {
        return wCategories;
    }
}
