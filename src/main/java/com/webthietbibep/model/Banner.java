package com.webthietbibep.model;

public class Banner {
    private int id ;
    private String title ;
    private String description;
    private String image ;
    public byte isActive;

    public Banner() {
    }

    public Banner(int id, String title, String description, String image, byte isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
