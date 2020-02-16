package com.e.myebook.Model;

public class Book {
    private String title;
    private String price;
    private String writer;
    private String thumbnailHd;
    //private String date;

    public Book(String title, String price, String writer, String thumbnailHd) {
        this.title = title;
        this.price = price;
        this.writer = writer;
        this.thumbnailHd = thumbnailHd;
        //this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getWriter() {
        return writer;
    }

    public String getThumbnailHd() {
        return thumbnailHd;
    }
}