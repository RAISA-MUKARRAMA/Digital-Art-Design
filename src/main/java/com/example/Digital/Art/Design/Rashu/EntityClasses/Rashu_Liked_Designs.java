package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`liked designs`")

public class Rashu_Liked_Designs {
    @Id
    @ManyToOne
    @JoinColumn(name = "Design ID", referencedColumnName = "Design_Code")
    private Rashu_Design design;

    @ManyToOne
    @JoinColumn(name = "User ID", referencedColumnName = "user_id")
    private Rashu_User user;

    public Rashu_Design getDesign() {
        return design;
    }

    public void setDesign(Rashu_Design design) {
        this.design = design;
    }

    public Rashu_User getUser() {
        return user;
    }

    public void setUser(Rashu_User user) {
        this.user = user;
    }
}
