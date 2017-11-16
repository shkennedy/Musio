package com.sbu.webspotify.service;

import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.Song;
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
        user.addPlaylistToFavories(playlist);
        userService.persistUser(user);
        return playlist;
	}

	public boolean addSongToPlaylist(User user, int playlistId, int songId) {
        Playlist p = playlistRepository.findById(playlistId);
        Song s = songService.getSongById(songId);

        if(p == null || s == null) {
            return false;
        }
        if(!p.getIsCollaborative() && !p.getOwner().equals(user)){
            return false;
        }

        p.addSong(s);
        persistPlaylist(p);
        return true;
    }

    public boolean removeSongFromPlaylist(User user, int playlistId, int songId) {
        Playlist p = playlistRepository.findById(playlistId);
        Song s = songService.getSongById(songId);

        if(p == null || s == null) {
            return false;
        }
        if(!p.getIsCollaborative() && !p.getOwner().equals(user)){
            return false;
        }

        boolean retValue = p.removeSong(s);
        persistPlaylist(p);
        return retValue;
    }
    
    public void persistPlaylist(Playlist p) {
        playlistRepository.save(p);
    }

}
