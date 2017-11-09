package com.sbu.webspotify.service;

import com.sbu.webspotify.domain.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.ArtistRepository;

import java.util.Set;

@Service("artistService")
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	public Artist getArtistById(int id) {
        return artistRepository.findById(id);
    }

    // public Set<Artist> getRelatedArtistsById(int id) {
    //     Artist artistToRelate = artistRepository.findById(id);
    //     if (artistToRelate == null) {
    //         throw new IllegalArgumentException("No artist with id: " + id);
    //     }

    //     artistToRelate;
    // }
}
