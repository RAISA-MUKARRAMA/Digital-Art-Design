package com.example.Digital.Art.Design.Raisa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="designer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Add this annotation
public class DesignerD {

    @Id
    @Column(name = "designer_id")
    private long designerId;
    @Column(name="password")
    private String password;
    @Column(name="designer_name")
    private String designerName;
    @Lob
    @Column(name="profile_photo", columnDefinition = "longblob")
    private byte[] profilePhoto;
    @Column(name="bio")
    private String bio;
    @Column(name="contact_no")
    private String contactNo;
    @Column(name="email_address")
    private String emailAddress;
    @Column(name="address")
    private String address;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "works for",
            joinColumns = @JoinColumn(name = "Designer_Id"),
            inverseJoinColumns = @JoinColumn(name = "Ctgr_Code")
    )
    private Set<CategoryD> categories = new HashSet<>();

//    @ManyToMany(mappedBy = "designerDMap")
//    private Set<AdminD> adminDS;

    public DesignerD() {
        super();
    }

    public DesignerD(long designerId, String password, String designerName, byte[] profilePhoto,
                     String bio, String contactNo, String emailAddress, String address,
                     LocalDate dateOfBirth) {
        this.designerId = designerId;
        this.password = password;
        this.designerName = designerName;
        this.profilePhoto = profilePhoto;
        this.bio = bio;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.categories = categories;
        //this.adminDS = adminDS;
    }

    public long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(long designerId) {
        this.designerId = designerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<CategoryD> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryD> categories) {
        this.categories = categories;
    }

//    public Set<AdminD> getAdminDS() {
//        return adminDS;
//    }
//
//    public void setAdminDS(Set<AdminD> adminDS) {
//        this.adminDS = adminDS;
//    }

    //    @Override
//    public String toString() {
//        return "DesignerD{" +
//                "designerId=" + designerId +
//                ", password='" + password + '\'' +
//                ", designerName='" + designerName + '\'' +
//                ", profilePhoto=" + Arrays.toString(profilePhoto) +
//                ", bio='" + bio + '\'' +
//                ", contactNo='" + contactNo + '\'' +
//                ", emailAddress='" + emailAddress + '\'' +
//                ", address='" + address + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                ", categories=" + categories +
//                '}';
//    }

    @Override
    public String toString() {
        return "DesignerD{" +
                "designerId=" + designerId +
                ", password='" + password + '\'' +
                ", designerName='" + designerName + '\'' +
                ", profilePhoto=" + Arrays.toString(profilePhoto) +
                ", bio='" + bio + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}


