package com.example.demo.Database;

public class Reservation {
    private int res_num;
    private String acc_usern;
    private int table_num;
    private String date;
    private String time;

    public Reservation(int res_num, String acc_usern, int table_num, String date, String time) {
        this.res_num = res_num;
        this.acc_usern = acc_usern;
        this.table_num = table_num;
        this.date = date;
        this.time = time;
    }
    public Reservation(int res_num, String[] data) {
        this.res_num = res_num;
        this.acc_usern = data[0];
        this.table_num = Integer.parseInt(data[1]);
        this.date = data[2];
        this.time = data[3];
    }

    //Getters for Reservation info.
    public int getResNum() {
        return res_num;
    }
    public String getAccUsern() {
        return acc_usern;
    }
    public int getTableNum() {
        return table_num;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    //Setters for Reservation info.
    public void setResNum(int res_num) {
        this.res_num = res_num;
    }
    public void setAccUsern(String acc_usern) {
        this.acc_usern = acc_usern;
    }
    public void setTableNum(int table_num) {
        this.table_num = table_num;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
