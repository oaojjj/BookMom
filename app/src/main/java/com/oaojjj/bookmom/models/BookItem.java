package com.oaojjj.bookmom.models;

public class BookItem {
    private String title;
    private String category;
    private String rental;
    private String bno;
    public BookItem(String title, String category, String rental,String bno) {
        this.title = title;
        this.category = category;
        this.rental = rental;
        this.bno=bno;
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

    public String getbno() { return bno;  }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }
    public void setBno(java.lang.String bno) {
        this.bno = bno;
    }
}
