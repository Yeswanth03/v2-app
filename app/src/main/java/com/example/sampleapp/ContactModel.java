package com.example.sampleapp;

public class ContactModel {
    int img;
    String name;
    String val;

    public ContactModel(int img, String name){
        this.name = name;
        this.img = img;
        //this.val=val;

    }
    public ContactModel(int img, String name,String val){
        this.name = name;
        this.img = img;
        this.val=val;



    }
    public ContactModel(String name){
        this.name = name;
    }
}
