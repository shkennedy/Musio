package com.sbu.webspotify.service;

import java.util.Arrays;
import java.util.HashSet;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.Genre;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.Role;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.AlbumRepository;
import com.sbu.webspotify.repo.ArtistRepository;
import com.sbu.webspotify.repo.GenreRepository;
import com.sbu.webspotify.repo.PlaylistRepository;
import com.sbu.webspotify.repo.RoleRepository;
import com.sbu.webspotify.repo.SongRepository;
import com.sbu.webspotify.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	public ApiResponseObject addFavoriteSong(User user, Integer songId) {
		ApiResponseObject response = new ApiResponseObject();
		Song s = songRepository.findById(songId);
		if(s == null) {
			response.setSuccess(false);
			response.setMessage("No song with id "+songId+" found.");
			return response;
		}
		user.addSongToFavories(s);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFavoriteSong(User user, Integer songId) {
		ApiResponseObject response = new ApiResponseObject();
		Song s = songRepository.findById(songId);
		if(s == null) {
			response.setSuccess(false);
			response.setMessage("No song with id "+songId+" found.");
			return response;
		}
		if(user.removeSongFromFavorites(s) == false) {
			response.setSuccess(false);
			response.setMessage("Song with id "+songId+" was not in your favorites list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject addFavoriteAlbum(User user, Integer albumId) {
		ApiResponseObject response = new ApiResponseObject();
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			response.setSuccess(false);
			response.setMessage("No album with id "+albumId+" found.");
			return response;
		}
		user.addAlbumToFavories(a);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFavoriteAlbum(User user, Integer albumId) {
		ApiResponseObject response = new ApiResponseObject();
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			response.setSuccess(false);
			response.setMessage("No album with id "+albumId+" found.");
			return response;
		}
		if(user.removeAlbumFromFavorites(a) == false) {
			response.setSuccess(false);
			response.setMessage("Album with id "+albumId+" was not in your favorites list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject addFavoriteArtist(User user, Integer artistId) {
		ApiResponseObject response = new ApiResponseObject();
		Artist a = artistRepository.findById(artistId);
		if(a == null) {
			response.setSuccess(false);
			response.setMessage("No artist with id "+artistId+" found.");
			return response;
		}
		user.addArtistToFavories(a);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFavoriteArtist(User user, Integer artistId) {
		ApiResponseObject response = new ApiResponseObject();
		Artist p = artistRepository.findById(artistId);
		if(p == null) {
			response.setSuccess(false);
			response.setMessage("No artist with id "+artistId+" found.");
			return response;
		}
		if(user.removeArtistFromFavorites(p) == false) {
			response.setSuccess(false);
			response.setMessage("Artist with id "+artistId+" was not in your favorites list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject addFollowedUser(User user, Integer userId) {
		ApiResponseObject response = new ApiResponseObject();
		User u = userRepository.findById(userId);
		if(u == null) {
			response.setSuccess(false);
			response.setMessage("No user with id "+userId+" found.");
			return response;
		}
		user.followUser(u);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFollowedUser(User user, Integer userId) {
		ApiResponseObject response = new ApiResponseObject();
		User u = userRepository.findById(userId);
		if(u == null) {
			response.setSuccess(false);
			response.setMessage("No user with id "+userId+" found.");
			return response;
		}
		if(user.unfollowUser(u) == false) {
			response.setSuccess(false);
			response.setMessage("User with id "+userId+" was not in your following list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public void persistUser(User user) {
		userRepository.save(user);
	}

	public ApiResponseObject addFavoritePlaylist(User user, Integer playlistId) {
		ApiResponseObject response = new ApiResponseObject();
		Playlist p = playlistRepository.findById(playlistId);
		if(p == null) {
			response.setSuccess(false);
			response.setMessage("No playlist with id "+playlistId+" found.");
			return response;
		}
		user.addPlaylistToFavories(p);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFavoritePlaylist(User user, Integer playlistId) {
		ApiResponseObject response = new ApiResponseObject();
		Playlist p = playlistRepository.findById(playlistId);
		if(p == null) {
			response.setSuccess(false);
			response.setMessage("No playlist with id "+playlistId+" found.");
			return response;
		}
		if(user.removePlaylistFromFavorites(p) == false) {
			response.setSuccess(false);
			response.setMessage("Playlist with id "+playlistId+" was not in your favorites list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject addFavoriteGenre(User user, Integer genreId) {
		ApiResponseObject response = new ApiResponseObject();
		Genre g = genreRepository.findById(genreId);
		if(g == null) {
			response.setSuccess(false);
			response.setMessage("No genre with id "+genreId+" found.");
			return response;
		}
		user.addGenreToFavories(g);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject removeFavoriteGenre(User user, Integer genreId) {
		ApiResponseObject response = new ApiResponseObject();
		Genre p = genreRepository.findById(genreId);
		if(p == null) {
			response.setSuccess(false);
			response.setMessage("No genre with id "+genreId+" found.");
			return response;
		}
		if(user.removeGenreFromFavorites(p) == false) {
			response.setSuccess(false);
			response.setMessage("Genre with id "+genreId+" was not in your favorites list.");
			return response;
		}
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject getBrowseContent(User user) {
		// TODO -- implement browse logic.
		
		return null;
	}

}
