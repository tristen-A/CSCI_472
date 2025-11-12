package com.example.demo.Database;

public class Account {
    private String username;
    private String password;
    private String name;
    private int auth;

    public Account(String username, String password, String name, int auth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.auth = auth;
    }
    public Account(String[] data) {
        this.username = data[0];
        this.password = data[1];
        this.name = data[2];
        this.auth = Integer.parseInt(data[3]);
    }

    //Getters for Menu Item info.
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public int getAuth() {
        return auth;
    }

    //Setters for Menu Item info.
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuth(int auth) {
        this.auth = auth;
    }
}
