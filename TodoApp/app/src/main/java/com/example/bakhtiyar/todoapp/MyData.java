package com.example.bakhtiyar.todoapp;

/**
 * Created by Bakhtiyar on 11/18/2016.
 */
public class MyData {

    Boolean flag;
    String data;
    String key;

    public MyData(String data, String key,Boolean flag) {
        this.data = data;
        this.key = key;
        this.flag=flag;
    }

    public MyData() {

    }


    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }
}
