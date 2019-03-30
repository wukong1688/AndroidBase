package com.jack.androidbase.bean;

public class Hero {

    private int hIcon;
    private String hName;

    public Hero() {
    }

    public Hero(int hIcon, String hName) {
        this.hIcon = hIcon;
        this.hName = hName;
    }

    public int gethIcon() {
        return hIcon;
    }

    public String gethName() {
        return hName;
    }

    public void sethIcon(int hIcon) {
        this.hIcon = hIcon;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

}