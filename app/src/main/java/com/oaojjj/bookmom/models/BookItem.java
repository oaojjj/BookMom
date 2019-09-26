package com.oaojjj.bookmom.models;

//TODO 재우형

/**
 *
 */
public class BookItem {
    private String title;
    private String category;
    private String rental;

    public BookItem(String title, String category, String rental) {
        this.title = title;
        this.category = category;
        this.rental = rental;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getRental() {
        return rental;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }
}
