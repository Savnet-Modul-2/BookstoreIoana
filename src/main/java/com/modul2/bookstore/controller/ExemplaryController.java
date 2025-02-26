package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.CreateExemplarsDTO;
import com.modul2.bookstore.dto.ExemplaryDTO;
import com.modul2.bookstore.entities.Exemplary;
import com.modul2.bookstore.mapper.ExemplaryMapper;
import com.modul2.bookstore.service.ExemplaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplars")
public class ExemplaryController {
    @Autowired
    private ExemplaryService exemplaryService;

    @PostMapping
    public ResponseEntity<List<ExemplaryDTO>> createExemplars(@RequestBody CreateExemplarsDTO createExemplarsDTO) {
        List<Exemplary> exemplars = exemplaryService.create(
                createExemplarsDTO.getPublisher(),
                createExemplarsDTO.getMaxRezervationDays(),
                createExemplarsDTO.getBookId(),
                createExemplarsDTO.getCounter()
        );

        List<ExemplaryDTO> exemplarsDto = ExemplaryMapper.toDtoList(exemplars);

        return ResponseEntity.ok(exemplarsDto);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ExemplaryDTO>> getExemplarsByBook(
            @PathVariable Long bookId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        List<ExemplaryDTO> exemplars = exemplaryService.findByBookId(bookId, page, size);
        return ResponseEntity.ok(exemplars);
    }

    @DeleteMapping("/{exemplaryId}")
    public ResponseEntity<?> deleteExemplary(@PathVariable Long exemplaryId) {
        exemplaryService.delete(exemplaryId);
        return ResponseEntity.ok().build();
    }
}
