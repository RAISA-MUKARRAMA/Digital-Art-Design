package com.example.Digital.Art.Design.Rashu.EntityClasses;

public class MessageWithFormattedDate {
    private Rashu_Message message;
    private String FormattedDate;

    public MessageWithFormattedDate(Rashu_Message message,String formattedDate) {
        this.message = message;
        this.FormattedDate = formattedDate;
    }

    public Rashu_Message getMessage() {
        return message;
    }

    public void setMessage(Rashu_Message message) {
        this.message = message;
    }

    public String getFormattedDate() {
        return FormattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        FormattedDate = formattedDate;
    }
}
