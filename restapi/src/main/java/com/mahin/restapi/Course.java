package com.mahin.restapi;

public class Course {
    private long id;
    private String name;
    private String author;
    public Course(long id, String author, String name) {
        this.id = id;
        this.author = author;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
