package com.sbu.webspotify.service;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.model.Album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.AlbumRepository;

import java.util.HashSet;
import java.util.Set;

@Service("albumService")
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	public Album getAlbumById(int id) {
        return albumRepository.findById(id);
    }

    public Set<Album> findAll() {
        return new HashSet<Album>(albumRepository.findAll());
    }

	public Set<AlbumIdentifier> searchByTitle(String query) {
		return albumRepository.findByNameContaining(query);
	}

}
