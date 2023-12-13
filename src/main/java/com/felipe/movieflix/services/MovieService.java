package com.felipe.movieflix.services;

import com.felipe.movieflix.dto.MovieDetailsDTO;
import com.felipe.movieflix.repositories.MovieRepository;
import com.felipe.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
