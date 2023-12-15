package com.felipe.movieflix.services;

import com.felipe.movieflix.dto.ReviewDTO;
import com.felipe.movieflix.entities.Movie;
import com.felipe.movieflix.entities.Review;
import com.felipe.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO review) {
        Review save = new Review();
        save.setText(review.getText());

        save.setMovie(new Movie());
        save.getMovie().setId(review.getMovieId());
        save.setUser(authService.authenticated());

        Long idSaved = repository.save(save).getId();
        review.setId(idSaved);
        review.setUserId(save.getUser().getId());
        review.setUserName(save.getUser().getName());
        review.setUserEmail(save.getUser().getEmail());

        return review;
    }
}
