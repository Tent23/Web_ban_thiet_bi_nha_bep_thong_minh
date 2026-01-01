package com.webthietbibep.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Article implements Serializable {
    private int id ;
    private String tip;
    private String title;
    private String content;
    private int category_id;
    private int likecount ;
    private String image;
    private LocalDate create_date;

    public Article() {
    }

    public Article(int id, String tip, String title, String content, int category_id, int likecount, String image, LocalDate create_date) {
        this.id = id;
        this.tip = tip;
        this.title = title;
        this.content = content;
        this.category_id = category_id;
        this.likecount = likecount;
        this.image = image;
        this.create_date = create_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public  String formatDate(LocalDate ld){
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ld.format(dinhDang);
    }


}
