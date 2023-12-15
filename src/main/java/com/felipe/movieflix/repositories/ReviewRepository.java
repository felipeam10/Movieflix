package com.felipe.movieflix.repositories;

import com.felipe.movieflix.dto.MovieCardDTO;
import com.felipe.movieflix.dto.MovieDetailsDTO;
import com.felipe.movieflix.dto.ReviewWithUserNameDTO;
import com.felipe.movieflix.entities.Movie;
import com.felipe.movieflix.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT new com.felipe.movieflix.dto.ReviewWithUserNameDTO(rvw.id, rvw.text, usr.name)
        FROM Review rvw
            INNER JOIN User usr ON usr.id = rvw.user.id
        WHERE rvw.movie.id = :movieId
    """)
    List<ReviewWithUserNameDTO> searchByMovieId(Long movieId);
}
