package com.sbu.webspotify.service;

import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.model.Artist;

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
	
    public Set<ArtistIdentifier> searchByName(String query) {
        return artistRepository.findByNameContaining(query);
    }
	
    public int getFavoritesCountForArtist(int artistId) { 
        return artistRepository.findFavoriteCountForArtist(artistId);
    }
	
}
