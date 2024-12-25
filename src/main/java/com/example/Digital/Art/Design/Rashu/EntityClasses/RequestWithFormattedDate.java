package com.example.Digital.Art.Design.Rashu.EntityClasses;

public class RequestWithFormattedDate {
    private String formattedDate;
    private Rashu_RequestTo request;

    public RequestWithFormattedDate(Rashu_RequestTo request,String formattedDate) {
        this.request = request;  this.formattedDate = formattedDate;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public Rashu_RequestTo getRequest() {
        return request;
    }

    public void setRequest(Rashu_RequestTo request) {
        this.request = request;
    }
}
