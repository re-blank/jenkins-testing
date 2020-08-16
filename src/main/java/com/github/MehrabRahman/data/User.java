package com.github.MehrabRahman.data;

public class User {
    private int id;
    private String username;
    private String password;
    private Role authrole;

    public User() {
    }

    public User(int id, String username, String password, Role authrole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authrole = authrole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getAuthrole() {
        return authrole;
    }

    public void setAuthrole(Role authrole) {
        this.authrole = authrole;
    }

}