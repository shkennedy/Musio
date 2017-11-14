package com.sbu.webspotify.service;

import com.sbu.webspotify.model.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.GenreRepository;

import java.util.HashSet;
import java.util.Set;

@Service("genreService")
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	public Genre getGenreById(int id) {
        return genreRepository.findById(id);
    }

    public Set<Genre> findAll() {
        return new HashSet<Genre>(genreRepository.findAll());
    }

}
