package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
public class UserOrder {
    @Id
    @Column(name = "order_code")
    public Long order_id;

    @Column(name = "designer_id")
    public Long d_id;

    @Column(name = "Description")
    public String description;

    @Column(name = "Title")
    public String title;

    @Column(name = "user_id")
    public Long uId;

    @Column(name = "design_code")
    public Long design_code;

    @Column(name = "Date")
    public LocalDateTime order_date;

    @Column(name = "Approved")
    public int approved;

    public UserOrder(){

    }
    public UserOrder(Long order_id, Long designer_id, String description,
                     Long user_id, LocalDateTime date, int approved){
        this.order_id = order_id;
        this.d_id = designer_id;
        this.description = description;
        this.uId = user_id;
        this.order_date = date;
        this.approved = approved;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getD_id() {
        return d_id;
    }

    public void setD_id(Long d_id) {
        this.d_id = d_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getU_id() {
        return uId;
    }

    public void setU_id(Long u_id) {
        this.uId = u_id;
    }

    public Long getDesign_code() {
        return design_code;
    }

    public void setDesign_code(Long design_code) {
        this.design_code = design_code;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }


}
