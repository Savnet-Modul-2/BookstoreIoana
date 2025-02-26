package com.modul2.bookstore.dto;

public class ExemplaryDTO {
    private Long id;
    private String publisher;
    private Integer maxRezervationDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getMaxRezervationDays() {
        return maxRezervationDays;
    }

    public void setMaxRezervationDays(Integer maxRezervationDays) {
        this.maxRezervationDays = maxRezervationDays;
    }
}
