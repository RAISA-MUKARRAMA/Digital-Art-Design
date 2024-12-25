package com.example.Digital.Art.Design.Arupa.EntityClasses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "counter")
public class UserCounter {
    @Id
    @Column(name = "count_number ")
    private Long id;
    @Column(name = "Designer_Id_curr ")
    private Long Designer_Id_curr ;
    @Column(name = "User_Id_curr")
    private Long User_Id_curr ;

    @Column(name="Contest_Code_curr")
    private Long Cnt_code;
    @Column(name="annc_code_curr")
    private Long annc_Code;
    @Column(name="Design_Code_curr")
    private Long Design_code;

    @Column(name="Order_Code_curr")
    private Long Order_Code;
    public UserCounter() {

        this.id=0L;
    }

    public Long getOrder_Code() {
        return Order_Code;
    }

    public Long getAnnc_Code() {
        return annc_Code;
    }

    public Long getCnt_code() {
        return Cnt_code;
    }

    public void setCnt_code(Long cnt_code) {
        Cnt_code = cnt_code;
    }

    public void setDesign_code(Long design_code) {
        Design_code = design_code;
    }

    public void setAnnc_Code(Long annc_Code) {
        this.annc_Code = annc_Code;
    }

    public void setOrder_Code(Long order_Code) {
        Order_Code = order_Code;
    }

    public Long getDesign_code() {
        return Design_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDesigner_Id_curr() {
        return Designer_Id_curr;
    }

    public long getUser_Id_curr() {
        return User_Id_curr;
    }

    public void setUser_Id_curr(long user_Id_curr) {
        User_Id_curr = user_Id_curr;
    }

    public void setDesigner_Id_curr(long designer_Id_curr) {
        Designer_Id_curr = designer_Id_curr;
    }


}
