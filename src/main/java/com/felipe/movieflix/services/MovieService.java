package com.felipe.movieflix.services;

import com.felipe.movieflix.dto.MovieCardDTO;
import com.felipe.movieflix.dto.MovieDetailsDTO;
import com.felipe.movieflix.repositories.MovieRepository;
import com.felipe.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        return movieRepository.searchById(id).orElseThrow(
            () -> new ResourceNotFoundException("Movie not founded")
        );
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findByGenre(Map<String, String> params) {
        Long genreId = params.containsKey("genreId") ? Long.parseLong(params.get("genreId")) : null;
        Pageable pageable = PageRequest.of(
                params.containsKey("page") ? Integer.parseInt(params.get("page")) : 0,
                params.containsKey("size") ? Integer.parseInt(params.get("size")) : 6
        );

        Page<MovieCardDTO> result = movieRepository.searchAllOrderByTitle(pageable, genreId);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find movies");
        }

        return result;
    }

}
