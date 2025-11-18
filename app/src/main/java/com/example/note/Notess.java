package com.example.note;

public class Notess {
    private String name;
    private String content;

    public Notess(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
    @Override
    public String toString() {
        return name;
    }
}
