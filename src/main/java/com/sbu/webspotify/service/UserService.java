package com.sbu.webspotify.service;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.Genre;
import com.sbu.webspotify.model.Playlist;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbu.webspotify.model.Role;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.repo.AlbumRepository;
import com.sbu.webspotify.repo.ArtistRepository;
import com.sbu.webspotify.repo.GenreRepository;
import com.sbu.webspotify.repo.PlaylistRepository;
import com.sbu.webspotify.repo.RoleRepository;
import com.sbu.webspotify.repo.SongRepository;
import com.sbu.webspotify.repo.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private GenreRepository genreRepository;
	
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppConfig appConfig;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findUserByUsernameAndPassword(String username, String password) {
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		return userRepository.findByUsernameAndPassword(username, encryptedPassword);
	}

	public void createAdminUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(appConfig.adminUser);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	public void createBasicUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(appConfig.basicUser);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	public boolean addFavoriteSong(User user, Integer songId) {
		Song s = songRepository.findById(songId);
		if(s == null) {
			return false;
		}
		user.addSongToFavories(s);
		persistUser(user);
		return true;
	}

	public boolean removeFavoriteSong(User user, Integer songId) {
		Song s = songRepository.findById(songId);
		if(s == null) {			
			return false;
		}
		boolean returnValue = user.removeSongFromFavorites(s);
		persistUser(user);
		return returnValue;
	}

	public boolean addFavoriteAlbum(User user, Integer albumId) {
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			return false;
		}
		user.addAlbumToFavories(a);
		persistUser(user);
		return true;
	}

	public boolean removeFavoriteAlbum(User user, Integer albumId) {
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			return false;
		}
		boolean returnValue = user.removeAlbumFromFavorites(a);
		persistUser(user);
		return returnValue;
	}

	public boolean addFavoriteArtist(User user, Integer artistId) {
		Artist a = artistRepository.findById(artistId);
		if(a == null) {
			return false;
		}
		user.addArtistToFavories(a);
		persistUser(user);
		return true;
	}

	public boolean removeFavoriteArtist(User user, Integer artistId) {
		Artist a = artistRepository.findById(artistId);
		if(a == null) {
			return false;
		}
		boolean returnValue = user.removeArtistFromFavorites(a);
		persistUser(user);
		return returnValue;
	}

	public boolean addFollowedUser(User user, Integer userId) {
		User u = userRepository.findById(userId);
		if(u == null) {
			return false;
		}
		user.followUser(u);
		persistUser(user);
		return true;
	}

	public boolean removeFollowedUser(User user, Integer userId) {
		User u = userRepository.findById(userId);
		if(u == null) {
			return false;
		}
		boolean returnValue = user.unfollowUser(u);
		persistUser(user);
		return returnValue;
	}

	public void persistUser(User user) {
		userRepository.save(user);
	}

	public boolean addFavoritePlaylist(User user, Integer playlistId) {
		Playlist p = playlistRepository.findById(playlistId);
		if(p == null) {
			return false;
		}
		user.addPlaylistToFavories(p);
		persistUser(user);
		return true;
	}

	public boolean removeFavoritePlaylist(User user, Integer playlistId) {
		Playlist p = playlistRepository.findById(playlistId);
		if(p == null) {
			return false;
		}
		boolean returnValue = user.removePlaylistFromFavorites(p);
		persistUser(user);
		return returnValue;
	}

	public boolean addFavoriteGenre(User user, Integer genreId) {
		Genre g = genreRepository.findById(genreId);
		if(g == null) {
			return false;
		}
		user.addGenreToFavories(g);
		persistUser(user);
		return true;
	}

	public boolean removeFavoriteGenre(User user, Integer genreId) {
		Genre g = genreRepository.findById(genreId);
		if(g == null) {
			return false;
		}
		boolean returnValue = user.removeGenreFromFavorites(g);
		persistUser(user);
		return returnValue;
	}

}
