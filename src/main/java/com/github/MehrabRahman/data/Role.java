package com.github.MehrabRahman.data;

public class Role {
    private int id;
    private String authrole;

    public Role() {
    }

    public Role(int id, String authrole) {
        this.id = id;
        this.authrole = authrole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthrole() {
        return authrole;
    }

    public void setAuthrole(String authrole) {
        this.authrole = authrole;
    }

}