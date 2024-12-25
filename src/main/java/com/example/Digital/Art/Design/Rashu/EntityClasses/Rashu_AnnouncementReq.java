package com.example.Digital.Art.Design.Rashu.EntityClasses;
public class Rashu_AnnouncementReq {
    private String description;
    private String confirmPassword;
    private Long Annc_code;
    private String anc_Title;
    private Long id;
    // Constructors (default and parameterized)


    public Rashu_AnnouncementReq() {
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnc_code(Long annc_code) {
        Annc_code = annc_code;
    }

    public Long getAnnc_code() {
        return Annc_code;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }





    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnc_Title(String anc_Title) {
        this.anc_Title = anc_Title;
    }

    public String getDescription() {
        return description;
    }

    public String getAnc_Title() {
        return anc_Title;
    }
}
