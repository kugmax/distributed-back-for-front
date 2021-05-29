package com.kugmax.learn.frontapp.FrontApp.responses;

public class BookDetailsResponse {
    private String book;
    private String author;

    public BookDetailsResponse() {
    }

    public BookDetailsResponse(String book, String author) {
        this.book = book;
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
