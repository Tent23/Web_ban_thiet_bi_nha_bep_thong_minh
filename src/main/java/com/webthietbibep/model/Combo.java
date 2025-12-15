package com.webthietbibep.model;

public class Combo {

    private int comboId;
    private String name;
    private String description;
    private String imageUrl;

    // ===== GETTERS =====
    public int getComboId() {
        return comboId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // ===== SETTERS =====
    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
