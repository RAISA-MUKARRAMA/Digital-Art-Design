package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "admin")
public class AdminD {
    @Id
    @Column(name = "Admin_Id")
    private long Admin_Id;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Admin_Name")
    private String Admin_Name;
    @Column(name = "Bio")
    private String Bio;
    @Column(name = "Contact_No")
    private String Contact_No;
    @Column(name = "Email_Address")
    private String Email_Address;
    @Column(name = "Address")
    private String Address;
    @Column(name = "date_of_birth")
    private LocalDate date_of_birth;

//    @ManyToMany
//    @JoinTable(
//            name = "message",
//            joinColumns = @JoinColumn(name = "Admin_Id"),
//            inverseJoinColumns = @JoinColumn(name = "designerId")
//    )
//    @MapKeyJoinColumns({
//            @MapKeyJoinColumn(name = "Description"),
//            @MapKeyJoinColumn(name = "Date")
//    })
//    private Map<Integer, DesignerD> designerDMap = new HashMap<>();

    public AdminD() {
    }

    public AdminD(long admin_Id, String password, String admin_Name, String bio, String contact_No,
                  String email_Address, String address, LocalDate date_of_birth) {
        Admin_Id = admin_Id;
        Password = password;
        Admin_Name = admin_Name;
        Bio = bio;
        Contact_No = contact_No;
        Email_Address = email_Address;
        Address = address;
        this.date_of_birth = date_of_birth;
        //this.designerDMap = designerDMap;
    }

    public long getAdmin_Id() {
        return Admin_Id;
    }

    public void setAdmin_Id(long admin_Id) {
        Admin_Id = admin_Id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAdmin_Name() {
        return Admin_Name;
    }

    public void setAdmin_Name(String admin_Name) {
        Admin_Name = admin_Name;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

//    public Map<Integer, DesignerD> getDesignerDMap() {
//        return designerDMap;
//    }
//
//    public void setDesignerDMap(Map<Integer, DesignerD> designerDMap) {
//        this.designerDMap = designerDMap;
//    }

    @Override
    public String toString() {
        return "Admin{" +
                "Admin_Id=" + Admin_Id +
                ", Password='" + Password + '\'' +
                ", Admin_Name='" + Admin_Name + '\'' +
                ", Bio='" + Bio + '\'' +
                ", Contact_No='" + Contact_No + '\'' +
                ", Email_Address='" + Email_Address + '\'' +
                ", Address='" + Address + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
