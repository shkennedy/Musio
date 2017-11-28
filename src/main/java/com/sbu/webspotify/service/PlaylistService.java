package com.sbu.webspotify.service;

import java.util.HashSet;
import java.util.Set;

import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.PlaylistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playlistService")
public class PlaylistService {

	@Autowired
    private PlaylistRepository playlistRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SongService songService;

	public Playlist getPlaylistById(int id) {
        return playlistRepository.findById(id);
    }

    public Set<Playlist> getAllPlaylists() {
        return new HashSet<Playlist>(playlistRepository.findAll());
    }

	public Playlist createPlaylist(Playlist playlist, User user) {
        playlist.setOwner(user);
        user.addPlaylistToFavorites(playlist);
        userService.persistUser(user);
        return playlist;
	}

	public Playlist addSongToPlaylist(User user, int playlistId, int songId) {
        Playlist p = playlistRepository.findById(playlistId);
        Song s = songService.getSongById(songId);

        if(p == null || s == null) {
            return null;
        }
        if(!p.getIsCollaborative() && !p.getOwner().equals(user)){
            return null;
        }

        p.addSong(s);
        persistPlaylist(p);
        return p;
    }

    public Playlist removeSongFromPlaylist(User user, int playlistId, int songId) {
        Playlist p = playlistRepository.findById(playlistId);
        Song s = songService.getSongById(songId);

        if(p == null || s == null) {
            return null;
        }
        if(!p.getIsCollaborative() && !p.getOwner().equals(user)){
            return null;
        }

        boolean retValue = p.removeSong(s);
        if (!retValue) {
            return null;
        }
        persistPlaylist(p);
        return p;
    }
    
    public void persistPlaylist(Playlist p) {
        playlistRepository.save(p);
    }

	public Set<PlaylistIdentifier> searchByName(String query) {
		return playlistRepository.findByNameContaining(query);
	}

}
