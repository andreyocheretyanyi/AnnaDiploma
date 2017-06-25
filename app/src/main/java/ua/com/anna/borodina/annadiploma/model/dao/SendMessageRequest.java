package ua.com.anna.borodina.annadiploma.model.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMessageRequest {

    @SerializedName("notification")
    @Expose
    private Notification notification;
    @SerializedName("to")
    @Expose
    private String to;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
