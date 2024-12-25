package com.example.Digital.Art.Design.Rashu.EntityClasses;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Rashu_SignupRequest {

    private String signupAs;

    private LocalDateTime regTime;

    private String name;

    private String phone;

    private String email;

    private String password;


    private LocalDate dob;
    public Rashu_SignupRequest() {
        this.regTime = LocalDateTime.now();
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate date) {
        this.dob = date;
    }

    public String getSignupAs() {
        return signupAs;
    }

    public void setSignupAs(String signupAs) {
        this.signupAs = signupAs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPass(String password) {
        this.password = password;
    }
    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

}
