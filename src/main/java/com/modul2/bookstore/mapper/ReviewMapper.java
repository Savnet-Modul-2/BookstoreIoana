package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.ReviewDTO;
import com.modul2.bookstore.entities.Review;

public class ReviewMapper {
    public static Review reviewDTO2Review(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setNota(reviewDTO.getNota());
        review.setDescription(reviewDTO.getDescription());
        review.setDateOfReview(reviewDTO.getDateOfReview());
        return review;
    }

    public static ReviewDTO review2ReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setNota(review.getNota());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setDateOfReview(review.getDateOfReview());
        if (review.getBook() != null) {
            reviewDTO.setBookDTO(BookMapper.book2BookDto(review.getBook()));
        }
        if (review.getUser() != null) {
            reviewDTO.setUserDTO(UserMapper.user2UserDTO(review.getUser()));
        }
        return reviewDTO;
    }
}
