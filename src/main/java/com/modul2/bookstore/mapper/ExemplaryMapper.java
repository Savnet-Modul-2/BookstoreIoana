package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.ExemplaryDTO;
import com.modul2.bookstore.entities.Exemplary;

import java.util.List;
import java.util.stream.Collectors;

public class ExemplaryMapper {
    public static Exemplary exemplaryDto2Exemplary(ExemplaryDTO exemplaryDTO) {
        Exemplary exemplary = new Exemplary();
        exemplary.setPublisher(exemplaryDTO.getPublisher());
        exemplary.setMaxRezervationDays(exemplaryDTO.getMaxRezervationDays());
        return exemplary;
    }

    public static ExemplaryDTO exemplary2ExemplaryDto(Exemplary exemplary) {
        ExemplaryDTO exemplaryDTO = new ExemplaryDTO();
        exemplaryDTO.setId(exemplary.getId());
        exemplaryDTO.setPublisher(exemplary.getPublisher());
        exemplaryDTO.setMaxRezervationDays(exemplary.getMaxRezervationDays());
        return exemplaryDTO;
    }

    public static List<ExemplaryDTO> toDtoList(List<Exemplary> exemplaries) {
        return exemplaries.stream().map(ExemplaryMapper::exemplary2ExemplaryDto).collect(Collectors.toList());
    }
}
