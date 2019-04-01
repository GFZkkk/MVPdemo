package com.example.gaofengze.demo.model.bean;

/**
 * Created by gaofengze on 2019/3/7
 */
public class TestData {
    private int type;
    private String title;

    public TestData(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}