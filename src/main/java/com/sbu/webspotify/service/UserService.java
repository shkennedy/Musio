package com.sbu.webspotify.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sbu.webspotify.conf.AppConfig;
import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.dto.identifier.UserIdentifier;
import com.sbu.webspotify.dto.identifier.UserHistoryItemIdentifier;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.File;
import com.sbu.webspotify.model.Genre;
import com.sbu.webspotify.model.MimeType;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.Role;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.AlbumRepository;
import com.sbu.webspotify.repo.ArtistRepository;
import com.sbu.webspotify.repo.GenreRepository;
import com.sbu.webspotify.repo.MimeTypeRepository;
import com.sbu.webspotify.repo.PlaylistRepository;
import com.sbu.webspotify.repo.RoleRepository;
import com.sbu.webspotify.repo.SongRepository;
import com.sbu.webspotify.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	private EmailService emailService;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private MimeTypeRepository mimeTypeRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AppConfig appConfig;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
    }
    
    public User findUserById(int id) {
		return userRepository.findById(id);
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
    
    public void deleteUser(User user) {
        userRepository.deleteUserById(user.getId());
    }

    public ApiResponseObject sendChangePasswordEmail(User user, int securityCode) {
        ApiResponseObject response = new ApiResponseObject();
        userRepository.save(user);
        response.setSuccess(
            emailService.sendSimpleEmailToUser(user, 
                                               appConfig.changePasswordSubject,
                                               appConfig.changePasswordBody + securityCode));
        return response;
    }

    public ApiResponseObject tryChangePassword(User user, int knownSecurityCode,
                                               int securityCode, String newPassword) {
        ApiResponseObject response = new ApiResponseObject();
        if (knownSecurityCode == securityCode) {
            user.setPassword(newPassword);
            userRepository.save(user);
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

	@Transactional
	public ApiResponseObject addFavoriteSong(User user, Integer songId) {
		ApiResponseObject response = new ApiResponseObject();
		Song s = songRepository.findById(songId);
		if(s == null) {
			response.setSuccess(false);
			response.setMessage("No song with id "+songId+" found.");
			return response;
		}
		user.addSongToFavorites(s);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	@Transactional
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
		userRepository.deleteFavoriteSongRecord(user.getId(), s.getId());
		response.setSuccess(true);
		return response;
	}

	@Transactional
	public ApiResponseObject addFavoriteAlbum(User user, Integer albumId) {
		ApiResponseObject response = new ApiResponseObject();
		Album a = albumRepository.findById(albumId);
		if(a == null) {
			response.setSuccess(false);
			response.setMessage("No album with id "+albumId+" found.");
			return response;
		}
		user.addAlbumToFavorites(a);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	@Transactional
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
		userRepository.deleteFavoriteAlbumRecord(user.getId(), a.getId());
		response.setSuccess(true);
		return response;
	}

	@Transactional
	public ApiResponseObject addFavoriteArtist(User user, Integer artistId) {
		ApiResponseObject response = new ApiResponseObject();
		Artist a = artistRepository.findById(artistId);
		if(a == null) {
			response.setSuccess(false);
			response.setMessage("No artist with id "+artistId+" found.");
			return response;
		}
		user.addArtistToFavorites(a);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	@Transactional
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
		userRepository.deleteFavoriteArtistRecord(user.getId(), p.getId());		
		response.setSuccess(true);
		return response;
	}

	@Transactional
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

	@Transactional
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
		userRepository.deleteFollowingUserRecord(user.getId(), u.getId());		
		response.setSuccess(true);
		return response;
	}

	public void persistUser(User user) {
		user = userRepository.save(user);
		userRepository.cleanFavoriteSongs();
		userRepository.cleanFavoriteAlbums();
		userRepository.cleanFavoriteArtists();
		userRepository.cleanFavoriteGenres();
		userRepository.cleanFollowingUsers();
		userRepository.cleanFavoritePlaylists();
		userRepository.flush();
	}

	@Transactional
	public ApiResponseObject addFavoritePlaylist(User user, Integer playlistId) {
		ApiResponseObject response = new ApiResponseObject();
		Playlist p = playlistRepository.findById(playlistId);
		if(p == null) {
			response.setSuccess(false);
			response.setMessage("No playlist with id "+playlistId+" found.");
			return response;
		}
		user.addPlaylistToFavorites(p);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	@Transactional
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
		userRepository.deleteFavoritePlaylistRecord(user.getId(), p.getId());
		response.setSuccess(true);
		return response;
	}

	@Transactional
	public ApiResponseObject addFavoriteGenre(User user, Integer genreId) {
		ApiResponseObject response = new ApiResponseObject();
		Genre g = genreRepository.findById(genreId);
		if(g == null) {
			response.setSuccess(false);
			response.setMessage("No genre with id "+genreId+" found.");
			return response;
		}
		user.addGenreToFavorites(g);
		persistUser(user);
		response.setSuccess(true);
		return response;
	}

	@Transactional
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
		userRepository.deleteFavoriteGenreRecord(user.getId(), p.getId());
		response.setSuccess(true);
		return response;
	}

	public ApiResponseObject getBrowseContent(User user) {
		// TODO -- implement browse logic.
		
		return null;
    }

    public ApiResponseObject getFollowedUsersHistoryTails(User user) {
        ApiResponseObject response = new ApiResponseObject();

        ArrayList<UserHistoryItemIdentifier> historyTails = new ArrayList<>();

        UserHistoryItemIdentifier historyItem;
        for (User followedUser : user.getFollowedUsers()) {
            SongIdentifier song = getListeningHistoryTail(followedUser.getId());
            if (song != null) {
                historyItem = new UserHistoryItemIdentifier();
                historyItem.id = song.getId();
                historyItem.title = song.getTitle();
                historyItem.userId = followedUser.getId();
                historyItem.albumId = song.getAlbumId();
                historyItem.albumTitle = song.getAlbumTitle();
                historyItem.duration = song.getDuration();
                historyItem.artistName = song.getArtistName();
                historyItem.artistId = song.getArtistId();
                historyTails.add(historyItem);
            }
        }
        response.setSuccess(true);
        response.setResponseData(historyTails);
        return response;
    }
    
    public boolean getIsAdmin(User user) {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals(appConfig.adminUser)) {
                return true;
            }
        }
        return false;
    }

    public boolean makeUserPremium(User user) {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals(appConfig.premiumUser)) {
                return false;
            }
        }

        Role premiumUserRole = roleRepository.findByRole(appConfig.premiumUser);
        user.setRoles(new HashSet<Role>(Arrays.asList(premiumUserRole)));
        userRepository.save(user);
        return true;
    }

	public boolean userExists(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean userExists(int id) {
		return userRepository.existsById(id);
	}

	public Set<SongIdentifier> getMostRecentlyFavoritedSongs(int userId, int numElements) {
		return songRepository.findRecentlyFavoritedSongsByUser(userId, numElements);
	}

	public Set<AlbumIdentifier> getMostRecentlyFavoritedAlbums(int userId, int numElements) {
		return albumRepository.findRecentlyFavoritedAlbumsByUser(userId, numElements);
	}

	public Set<ArtistIdentifier> getMostRecentlyFavoritedArtists(int userId, int numElements) {
		return artistRepository.findRecentlyFavoritedArtistsByUser(userId, numElements);
	}

	public Set<PlaylistIdentifier> getMostRecentlyFavoritedPlaylists(int userId, int numElements) {
		return playlistRepository.findRecentlyFavoritedPlaylistsByUser(userId, numElements);
	}

	public void addSongToHistory(int userId, int songId) {
		userRepository.addSongToUserHistory(userId, songId);
	}

	public Set<SongIdentifier> getListeningHistory(int userId) {
		return songRepository.getListeningHistoryForUser(userId, appConfig.listeningHistoryToQuery);
    }
    
    public SongIdentifier getListeningHistoryTail(int userId) {
        System.out.println("user: " + userId + " tail: " + songRepository.getListeningHistoryForUser(userId, 1));
        Set<SongIdentifier> historyTail = songRepository.getListeningHistoryForUser(userId, 1);
        if (!historyTail.isEmpty()) {
            return historyTail.iterator().next();
        }
		return null;
    }

	public Set<UserIdentifier> getFollowedUsers(int userId) {
		return userRepository.getFollowedUsers(userId);
    }
    
    public Set<UserIdentifier> searchByUsername(String queryString) {
        return userRepository.findByUsernameContaining(queryString);
    }

	@Transactional
	public void updateProfileImage(User user, MultipartFile profileImageFile) throws IOException {
		// PNG images only.
		MimeType mimeType = mimeTypeRepository.findBySubtype(appConfig.png);
		File profileImage = fileService.uploadFile(profileImageFile.getBytes(), mimeType);
		user.setProfileImage(profileImage);
		persistUser(user);
	}

	public int getFollowerCount(int userId) {
		return userRepository.findFollowerCountForUser(userId);
	}

	public boolean checkForProfileImage(int userId) {
		Integer result = userRepository.checkForProfileImage(userId);
		return (result != null);
	}


}
