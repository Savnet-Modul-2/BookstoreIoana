package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.LibraryDTO;
import com.modul2.bookstore.dto.ReviewDTO;
import com.modul2.bookstore.dto.validation.ValidationOrder;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.entities.Review;
import com.modul2.bookstore.mapper.LibraryMapper;
import com.modul2.bookstore.mapper.ReviewMapper;
import com.modul2.bookstore.repository.ReviewRepository;
import com.modul2.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("/create-review-from/{userId}/for/{bookId}")
    public ResponseEntity<?> createReviewFromUserForBook(@Validated(ValidationOrder.class)
                                                         @PathVariable Long userId,
                                                         @PathVariable Long bookId,
                                                         @RequestBody ReviewDTO reviewDTO) {
        Review reviewToCreate = ReviewMapper.reviewDTO2Review(reviewDTO);
        Review createdReview = reviewService.createReviewFromUserForBook(userId, bookId, reviewToCreate);
        ReviewDTO createdReviewDTO = ReviewMapper.review2ReviewDTO(createdReview);
        return ResponseEntity.ok(createdReviewDTO);
    }

    @PutMapping("/modify-review/{reviewId}")
    public ResponseEntity<?> modifyReview(@PathVariable Long reviewId,
                                          @RequestBody ReviewDTO reviewDTO) {
        Review reviewToModify = ReviewMapper.reviewDTO2Review(reviewDTO);
        Review modifiedReview = reviewService.modifyReview(reviewId, reviewToModify);
        ReviewDTO modifiedReviewDTO = ReviewMapper.review2ReviewDTO(modifiedReview);

        return ResponseEntity.ok(modifiedReviewDTO);
    }

    @DeleteMapping("/delete-review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated-reviews/{bookId}")
    public ResponseEntity<?> paginatedReviewsForBook(@PathVariable Long bookId,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        Page<Review> reviews = reviewRepository.paginatedReviewsForBook(bookId, PageRequest.of(page, size));
        Page<ReviewDTO> reviewDTOS = reviews.map(ReviewMapper::review2ReviewDTO);
        return ResponseEntity.ok(reviewDTOS);
    }
}
