package com.sbu.webspotify.service;

import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.SongRepository;

import java.util.HashSet;
import java.util.Set;

@Service("songService")
public class SongService {

	@Autowired
	private SongRepository songRepository;

	public Song getSongById(int id) {
        return songRepository.findById(id);
    }

    public Set<Song> findAll() {
        return new HashSet<Song>(songRepository.findAll());
    }

	public Set<SongIdentifier> searchByTitle(String query) {
		return songRepository.findByNameContaining(query);
	}

}
