package com.sbu.webspotify.service;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
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

	public boolean addFavoriteAlbum(User user, Integer albumId) {
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			return false;
		}
		user.addAlbumToFavories(a);
		persistUser(user);
		return true;
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

	public void persistUser(User user) {
		userRepository.save(user);
	}
}
