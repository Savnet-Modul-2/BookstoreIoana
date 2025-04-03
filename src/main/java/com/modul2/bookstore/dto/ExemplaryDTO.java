package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.BasicValidation;
import jakarta.validation.constraints.NotNull;

import java.util.Base64;

public class ExemplaryDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String publisher;
    @NotNull(groups = BasicValidation.class)
    private Integer maxRezervationDays;

    private BookDTO bookDTO;

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

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}
