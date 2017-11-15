package com.sbu.webspotify.service;

import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.repo.PlaylistRepository;

import java.util.HashSet;
import java.util.Set;

@Service("playlistService")
public class PlaylistService {

	@Autowired
    private PlaylistRepository playlistRepository;
    
    @Autowired
	private UserService userService;

	public Playlist getPlaylistById(int id) {
        return playlistRepository.findById(id);
    }

    public Set<Playlist> getAllPlaylists() {
        return new HashSet<Playlist>(playlistRepository.findAll());
    }

	public Playlist createPlaylist(Playlist playlist, User user) {
        playlist.setOwner(user);
        user.addPlaylistToFavories(playlist);
        userService.persistUser(user);
        return playlist;
	}

}
