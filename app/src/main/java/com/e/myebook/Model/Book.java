package com.e.myebook.Model;

public class Book {
    private String title;
    private String price;
    private String writer;
    private String thumbnailHd;
    private String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getThumbnailHd() {
        return thumbnailHd;
    }

    public void setThumbnailHd(String thumbnailHd) {
        this.thumbnailHd = thumbnailHd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}