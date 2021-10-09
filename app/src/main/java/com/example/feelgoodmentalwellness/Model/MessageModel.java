package com.example.feelgoodmentalwellness.Model;

public class MessageModel {
    private String user;
    private String mssg;
    private String img;
    private String time;
    private String date;

    public MessageModel() {
    }

    public MessageModel(String mssg, String user, String img, String time, String date) {
        this.user = user;
        this.mssg = mssg;
        this.img = img;
        this.time = time;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "user='" + user + '\'' +
                ", mssg='" + mssg + '\'' +
                ", img='" + img + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
