package com.kugmax.learn.frontend.Frontend.model;


public class Book {
    private String id;
    private String title;
    private int pages;
    private String authorId;

    public Book() {
    }

    public Book(String id, String title, int pages, String authorId) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
    }

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String autorId) {
        this.authorId = autorId;
    }

    public Book withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public Book withAuthorId(String author) {
        this.setAuthorId(author);
        return this;
    }

    public Book withPages(int pages) {
        this.setPages(pages);
        return this;
    }
}
