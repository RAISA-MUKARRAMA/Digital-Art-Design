package com.example.Digital.Art.Design.Rashu.EntityClasses;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class Rashu_Order {

    @Id
    @Column(name="order_code")
    private Long order_code;
    @Column(name = "Date")
    private LocalDateTime date;

    @Column(name = "Approved")
    private Integer approved ;

    @ManyToOne
    @JoinColumn(name = "designer_id", referencedColumnName = "designer_id")
    private Rashu_Designer designer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Rashu_User user;

    @ManyToOne
    @JoinColumn(name = "design_code", referencedColumnName = "Design_Code")
    private Rashu_Design design;
    @Column(name="Description")
    private String description;

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Long getOrder_code() {
        return order_code;
    }

    public void setOrder_code(Long order_code) {
        this.order_code = order_code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Rashu_Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Rashu_Designer designer) {
        this.designer = designer;
    }

    public Rashu_User getUser() {
        return user;
    }

    public void setUser(Rashu_User user) {
        this.user = user;
    }

    public Rashu_Design getDesign() {
        return design;
    }

    public void setDesign(Rashu_Design design) {
        this.design = design;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
