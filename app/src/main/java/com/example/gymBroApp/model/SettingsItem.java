package com.example.gymBroApp.model;

public class SettingsItem {
    private int imgSrc;
    private String  fieldInput;

    public SettingsItem(int imgSrc,  String fieldInput) {
        this.imgSrc = imgSrc;

        this.fieldInput = fieldInput;
    }

    public SettingsItem() {
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }


    public String getFieldInput() {
        return fieldInput;
    }

    public void setFieldInput(String fieldInput) {
        this.fieldInput = fieldInput;
    }
}
