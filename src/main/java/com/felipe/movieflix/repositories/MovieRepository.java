package com.felipe.movieflix.repositories;

import com.felipe.movieflix.dto.MovieCardDTO;
import com.felipe.movieflix.dto.MovieDetailsDTO;
import com.felipe.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
        SELECT new com.felipe.movieflix.dto.MovieDetailsDTO(m.id, m.title, m.subTitle, m.year, m.imgUrl, m.synopsis, m.genre.id, m.genre.name)
        FROM Movie m
            INNER JOIN Genre g ON g.id = m.genre.id
        WHERE m.id = :id
    """)
    Optional<MovieDetailsDTO> searchById(Long id);

    @Query("""
        SELECT new com.felipe.movieflix.dto.MovieCardDTO(m)
        FROM Movie m
        WHERE (:genreId IS NULL OR m.genre.id = :genreId)
        ORDER BY m.title ASC
    """)
    Page<MovieCardDTO> searchAllOrderByTitle(Pageable pageable, @Param("genreId") Long genreId);
}
