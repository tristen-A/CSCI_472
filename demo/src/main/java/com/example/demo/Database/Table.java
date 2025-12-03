package com.example.demo.Database;

public class Table {
    private int table_num;
    private int capacity;
    private int price;
    private String location;
    private boolean reserved = false;

    public Table(int table_num, int capacity, int price, String location, boolean reserved) {
        this.table_num = table_num;
        this.capacity = capacity;
        this.price = price;
        this.location = location;
        this.reserved = reserved;
    }
    public Table(int table_num, String[] data) {
        this.table_num = table_num;
        this.capacity = Integer.parseInt(data[0]);
        this.price = Integer.parseInt(data[1]);
        this.location = data[2];
        this.reserved = Boolean.parseBoolean(data[3]);
    }

    //Getters for Menu Item info.
    public int getNumber() {
        return table_num;
    }
    public int getCap() {
        return capacity;
    }
    public int getPrice() {
        return price;
    }
    public String getLocation() {
        return location;
    }
    public boolean checkReservation() {
        return reserved;
    }

    //Setters for Menu Item info.
    public void setNum(int table_num) {
        this.table_num = table_num;
    }
    public void setCap(int capacity) {
        this.capacity = capacity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setReservation(boolean reserved) {
        this.reserved = reserved;
    }
}
