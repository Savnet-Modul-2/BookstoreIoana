package com.modul2.bookstore.dto;

public class CreateExemplarsDTO {
    private String publisher;
    private Integer maxRezervationDays;
    private Integer counter;
    private Long bookId;

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

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
