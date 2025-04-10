package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Book;
import com.modul2.bookstore.entities.Review;
import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.repository.BookRepository;
import com.modul2.bookstore.repository.ReviewRepository;
import com.modul2.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Review createReviewFromUserForBook(Long userId, Long bookId, Review reviewToCreate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("This user doesn't exist"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("This book doesn't exist"));

        reviewToCreate.setUser(user);
        reviewToCreate.setBook(book);
        reviewToCreate.setDateOfReview(LocalDateTime.now());
        Integer suma = 0;
        Integer count = book.getReviews().size();
        for (Review review : book.getReviews()) {
            suma += review.getNota();
        }
        Double medie = (double) (suma / count);
        book.setMedie(medie);

        return reviewRepository.save(reviewToCreate);
    }

    public Review modifyReview(Long reviewId, Review reviewToModify) {
        Review existentReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("This review doesn't exist"));
        existentReview.setNota(reviewToModify.getNota());
        existentReview.setDescription(reviewToModify.getDescription());

        return reviewRepository.save(existentReview);
    }

    public void deleteReview(Long reviewId) {
        Review reviewToDelete = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("This review doesn't exist"));

        reviewRepository.delete(reviewToDelete);
    }
}
