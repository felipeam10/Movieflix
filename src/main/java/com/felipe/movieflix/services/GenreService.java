package com.felipe.movieflix.services;

import com.felipe.movieflix.dto.GenreDTO;
import com.felipe.movieflix.entities.Genre;
import com.felipe.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository repository;

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {
        List<Genre> list = repository.findAll();
        return list.stream().map(x -> new GenreDTO(x)).toList();
    }
}
