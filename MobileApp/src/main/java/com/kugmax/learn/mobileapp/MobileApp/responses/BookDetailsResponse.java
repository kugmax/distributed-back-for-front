package com.kugmax.learn.mobileapp.MobileApp.responses;

public class BookDetailsResponse {
    private String bookTitle;
    private String authorName;

    public BookDetailsResponse() {
    }

    public BookDetailsResponse(String bookTitle, String authorName) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
