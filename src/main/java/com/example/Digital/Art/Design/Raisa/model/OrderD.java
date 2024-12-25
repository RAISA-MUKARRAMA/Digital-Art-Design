package com.example.Digital.Art.Design.Raisa.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class OrderD {
    @Id
    private long order_code;
    @Column(name = "designer_id")
    private long designer_id;
    @Column(name = "Description")
    private String Description;
    @Column(name = "Title")
    private String Title;
    @Column(name = "user_id")
    private long user_id;

    @ManyToOne
    @JoinColumn(name = "design_code", referencedColumnName = "design_code")
    private DesignD designD;
    @Column(name = "Date")
    private LocalDateTime Date;

    @Column(name = "Approved")
    private Integer Approved;

    public OrderD() {
    }

    public OrderD(long order_code, long designer_id, String description, String title, long user_id,
                  DesignD designD, LocalDateTime date, Integer approved) {
        this.order_code = order_code;
        this.designer_id = designer_id;
        Description = description;
        Title = title;
        this.user_id = user_id;
        this.designD = designD;
        Date = date;
        Approved = approved;
    }

    public long getOrder_code() {
        return order_code;
    }

    public void setOrder_code(long order_code) {
        this.order_code = order_code;
    }

    public long getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(long designer_id) {
        this.designer_id = designer_id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public DesignD getDesignD() {
        return designD;
    }

    public void setDesignD(DesignD designD) {
        this.designD = designD;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getApproved() {
        return Approved;
    }

    public void setApproved(Integer approved) {
        Approved = approved;
    }

    @Override
    public String toString() {
        return "OrderD{" +
                "order_code=" + order_code +
                ", designer_id=" + designer_id +
                ", Description='" + Description + '\'' +
                ", Title='" + Title + '\'' +
                ", user_id=" + user_id +
                ", designD=" + designD +
                ", Date=" + Date +
                ", Approved=" + Approved +
                '}';
    }
}
