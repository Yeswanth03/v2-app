package com.example.sampleapp;

public class ContactModel {
    int img;
    String name;

    public ContactModel(int img, String name){
        this.name = name;
        this.img = img;

    }
    public ContactModel(String name){
        this.name = name;
    }
}
