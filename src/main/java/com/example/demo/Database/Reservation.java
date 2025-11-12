package com.example.demo.Database;

public class Reservation {
    private int res_num;
    private int acc_num;
    private int table_num;
    private String date;
    private String time;

    public Reservation(int res_num, int acc_num, int table_num, String date, String time) {
        this.res_num = res_num;
        this.acc_num = acc_num;
        this.table_num = table_num;
        this.date = date;
        this.time = time;
    }
    public Reservation(int res_num, String[] data) {
        this.res_num = res_num;
        this.acc_num = Integer.parseInt(data[0]);
        this.table_num = Integer.parseInt(data[1]);
        this.date = data[2];
        this.time = data[3];
    }

    //Getters for Reservation info.
    public int getResNum() {
        return res_num;
    }
    public int getAccNum() {
        return acc_num;
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
    public void setAccNum(int acc_num) {
        this.acc_num = acc_num;
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
