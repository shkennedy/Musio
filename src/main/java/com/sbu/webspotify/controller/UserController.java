package com.sbu.webspotify.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.dto.identifier.UserIdentifier;
import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.model.CreditCardInfo;
import com.sbu.webspotify.model.Genre;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.Song;
import com.sbu.webspotify.model.Station;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.service.SongService;
import com.sbu.webspotify.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

	@Autowired
	private SongService songService;

    @RequestMapping(value={"/getUser"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getMyUser(HttpSession session){
		User user = (User) session.getAttribute("user");
		ApiResponseObject response = new ApiResponseObject();
		response.setResponseData(user);
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value={"/getUsername"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getMyUsername(HttpSession session){
		User user = (User) session.getAttribute("user");
		ApiResponseObject response = new ApiResponseObject();
		response.setResponseData(user.getUsername());
		response.setSuccess(true);
		return response;
	}

	@GetMapping(path = "/get/{var}")
	public @ResponseBody ApiResponseObject getAUser(@PathVariable String var) {
        ApiResponseObject response = new ApiResponseObject();
        User user = userService.findUserByUsername(var);
        response.setSuccess(user == null);
        response.setResponseData(user);
		return response;
	}

	@GetMapping(path="/whoami")
	public @ResponseBody User whoAmI(HttpSession session) {
		return (User) session.getAttribute("user");
    }

    @GetMapping(path="/password/requestChange")
    public @ResponseBody ApiResponseObject updatePassword(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int securityCode = user.hashCode();
        session.setAttribute("securityCode", securityCode);
        return userService.sendChangePasswordEmail(user, securityCode);
    }

    @RequestMapping(value="/password", method = RequestMethod.PUT, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject updatePassword(HttpSession session, 
                                                          @RequestParam int securityCode,
                                                          @RequestParam String newPassword) {
        User user = (User) session.getAttribute("user");
        int knownSecurityCode = (Integer) session.getAttribute("securityCode");
        return userService.tryChangePassword(user, knownSecurityCode, securityCode, newPassword);
    }
    
    @RequestMapping(value={"/{userId}"}, method = RequestMethod.DELETE)
    public @ResponseBody ApiResponseObject deleteUser(HttpSession session, @PathVariable int userId) {
        ApiResponseObject response = new ApiResponseObject();
        User userToDelete = userService.findUserById(userId);
        User currentUser = (User) session.getAttribute("user");
        System.out.println("userToDelete " + userToDelete + " || currentUser " + currentUser);
        if (userToDelete.equals(currentUser) || userService.getIsAdmin(currentUser)) {
            userService.deleteUser(userToDelete);
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

	/**
	 * Re-query the user stored in the session from the database.
	 */
	@RequestMapping(value={"/refreshCurrentUser"}, method = RequestMethod.GET)
	public @ResponseBody boolean refreshUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", userService.findUserByUsername(user.getUsername()));
		return true;
	}

	@RequestMapping(value={"/favorites/albums"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoriteAlbums(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Album> albums = user.getFavoriteAlbums();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(albums);
		return response;
	}

	@RequestMapping(value={"/favorites/songs"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoriteSongs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Song> songs = user.getFavoriteSongs();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(songs);
		return response;
	}

	@RequestMapping(value={"/favorites/playlists"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoritePlaylists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Playlist> playlists = user.getFavoritePlaylists();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(playlists);
		return response;
	}

	@RequestMapping(value={"/favorites/artists"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoriteArtists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Artist> artists = user.getFavoriteArtists();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(artists);
		return response;
	}

	@RequestMapping(value={"/favorites/genres"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoriteGenres(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Genre> genres = user.getFavoriteGenres();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(genres);
		return response;
	}

	@RequestMapping(value={"/favorites/stations"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFavoriteStations(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Set<Station> stations = user.getFavoriteStations();
		ApiResponseObject response = new ApiResponseObject();
		response.setSuccess(true);
		response.setResponseData(stations);
		return response;
	}

	@RequestMapping(value={"/favorites/add/genre/{genreId}"})
	public @ResponseBody ApiResponseObject addFavoriteGenre(HttpSession session, @PathVariable Integer genreId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteGenre(user, genreId);
	}

	@RequestMapping(value={"/favorites/remove/genre/{genreId}"})
	public @ResponseBody ApiResponseObject removeFavoriteGenre(HttpSession session, @PathVariable Integer genreId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteGenre(user, genreId);
	}

	@RequestMapping(value={"/favorites/add/song/{songId}"})
	public @ResponseBody ApiResponseObject addFavoriteSong(HttpSession session, @PathVariable Integer songId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteSong(user, songId);
	}

	@RequestMapping(value={"/favorites/remove/song/{songId}"})
	public @ResponseBody ApiResponseObject removeFavoriteSong(HttpSession session, @PathVariable Integer songId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteSong(user, songId);
	}

	@RequestMapping(value={"/favorites/add/album/{albumId}"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject addFavoriteAlbum(HttpSession session, @PathVariable Integer albumId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteAlbum(user, albumId);
	}

	@RequestMapping(value={"/favorites/remove/album/{albumId}"})
	public @ResponseBody ApiResponseObject removeFavoriteAlbum(HttpSession session, @PathVariable Integer albumId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteAlbum(user, albumId);
	}

	@RequestMapping(value={"/favorites/add/artist/{artistId}"})
	public @ResponseBody ApiResponseObject addFavoriteArtist(HttpSession session, @PathVariable Integer artistId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoriteArtist(user, artistId);
	}

	@RequestMapping(value={"/favorites/remove/artist/{artistId}"})
	public @ResponseBody ApiResponseObject removeFavoriteArtist(HttpSession session, @PathVariable Integer artistId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoriteArtist(user, artistId);
	}

	@RequestMapping(value={"/favorites/add/playlist/{playlistId}"})
	public @ResponseBody ApiResponseObject addFavoritePlaylist(HttpSession session, @PathVariable Integer playlistId) {
		User user = (User) session.getAttribute("user");
		return userService.addFavoritePlaylist(user, playlistId);
	}

	@RequestMapping(value={"/favorites/remove/playlist/{playlistId}"})
	public @ResponseBody ApiResponseObject removeFavoritePlaylist(HttpSession session, @PathVariable Integer playlistId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFavoritePlaylist(user, playlistId);
    }
    
    @RequestMapping(value={"/followedUsers"}, method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getFollowedUsers(HttpSession session) {
        ApiResponseObject response = new ApiResponseObject();
		int userId = (int) session.getAttribute("userId");
		Set<UserIdentifier> followedUsers = userService.getFollowedUsers(userId);
        response.setResponseData(followedUsers);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value={"{userId}/followedUsers"}, method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getFollowedUsers(@PathVariable Integer userId) {
        ApiResponseObject response = new ApiResponseObject();
		Set<UserIdentifier> followedUsers = userService.getFollowedUsers(userId);
        response.setResponseData(followedUsers);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value={"/followedUsers/history"}, method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getFollowedUsersHistoryTails(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.getFollowedUsersHistoryTails(user);
    }

	@RequestMapping(value={"/followUser/{userId}"})
	public @ResponseBody ApiResponseObject addFollowedUser(HttpSession session, @PathVariable Integer userId) {
		User user = (User) session.getAttribute("user");
		return userService.addFollowedUser(user, userId);
	}

	@RequestMapping(value={"/unfollowUser/{userId}"})
	public @ResponseBody ApiResponseObject unfollowedUser(HttpSession session, @PathVariable Integer userId) {
		User user = (User) session.getAttribute("user");
		return userService.removeFollowedUser(user, userId);
	}

	@RequestMapping(value={"/browse"})
	public @ResponseBody ApiResponseObject getBrowseData(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return userService.getBrowseContent(user);
	}

	@RequestMapping(value={"/addSongToHistory/{songId}"})
	public @ResponseBody ApiResponseObject addSongToHistory(@PathVariable int songId, HttpSession session) {
		ApiResponseObject response = new ApiResponseObject();		
		User user = (User) session.getAttribute("user");

		if(songService.songExists(songId) == false) {
			response.setSuccess(false);
			response.setMessage("No song found with id "+songId+".");
		}
		else{
			userService.addSongToHistory(user.getId(), songId);			
			response.setSuccess(true);
		}

		return response;
	}

	@RequestMapping(value={"/myListeningHistory"})
	public @ResponseBody ApiResponseObject getMyListeningHistory(HttpSession session) {
		ApiResponseObject response = new ApiResponseObject();
		User user = (User) session.getAttribute("user");
		Set<SongIdentifier> songs = userService.getListeningHistory(user.getId());
		if(songs == null) {
			response.setSuccess(false);
			response.setMessage("Could not fetch listening for user with id "+user.getId()+".");
		}
		else{
			response.setSuccess(true);
			response.setResponseData(songs);
		}

		return response;
	}

	@RequestMapping(value={"/listeningHistoryForUser/{userId}"})
	public @ResponseBody ApiResponseObject getUsersHistory(@PathVariable int userId) {
		ApiResponseObject response = new ApiResponseObject();

		if(userService.userExists(userId) == false) {
			response.setSuccess(false);
			response.setMessage("No user found with id "+userId+".");
			return response;
		}

		Set<SongIdentifier> songs = userService.getListeningHistory(userId);
		if(songs == null) {
			response.setSuccess(false);
			response.setMessage("Could not fetch listening for user with id "+userId+".");
		}
		else{
			response.setSuccess(true);
			response.setResponseData(songs);
		}

		return response;
	}

	/**
	 * set the current listening session to private
	 */
	@RequestMapping(value={"/enablePrivateMode"})
	public @ResponseBody ApiResponseObject enablePrivateMode(HttpSession session) {
		session.setAttribute("privateMode", true);
		return new ApiResponseObject(true, null, "Private mode is now ON.");
	}

	/**
	 * set the current listening session to public
	 */
	@RequestMapping(value={"/disablePrivateMode"})
	public @ResponseBody ApiResponseObject disablePrivateMode(HttpSession session) {
		session.setAttribute("privateMode", false);
		return new ApiResponseObject(true, null, "Private mode is now OFF.");
	}

	/**
	 * returns whether or not the current session is private
	 */
	@RequestMapping(value={"/getBrowsingMode"})
	public @ResponseBody ApiResponseObject checkBrowsingMode(HttpSession session) {
		boolean browsingMode = (boolean) session.getAttribute("privateMode");
		return new ApiResponseObject(true, null, browsingMode);
	}

	@RequestMapping(value="/uploadProfileImage", method=RequestMethod.POST)
	public @ResponseBody ApiResponseObject updateProfileImage(@RequestParam("profileImageFile") MultipartFile profileImageFile, HttpSession session){
		ApiResponseObject response = new ApiResponseObject();
		User currentUser = (User) session.getAttribute("user");
		try {
			userService.updateProfileImage(currentUser, profileImageFile);
			response.setSuccess(true);
		}
		catch(IOException e){
			e.printStackTrace();
			response.setMessage("Error uploading new profile image.");
			response.setSuccess(false);
		}

		return response;
	}

	@RequestMapping(value="/followerCount/{userId}", method=RequestMethod.GET)
	public @ResponseBody ApiResponseObject getFollowerCount(@PathVariable int userId){
		ApiResponseObject response = new ApiResponseObject();

		if(userService.userExists(userId) == false) {
			response.setSuccess(false);
			response.setMessage("No user found with id "+userId+".");
			return response;
		}

		response.setResponseData(userService.getFollowerCount(userId));
		response.setSuccess(true);

		return response;
	}

	@RequestMapping(value="/checkProfileImage/{userId}", method=RequestMethod.GET)
	public @ResponseBody ApiResponseObject checkForProfileImage(@PathVariable int userId){
		ApiResponseObject response = new ApiResponseObject();

		if(userService.userExists(userId) == false) {
			response.setSuccess(false);
			response.setMessage("No user found with id "+userId+".");
			return response;
		}

		response.setResponseData(userService.checkForProfileImage(userId));
		response.setSuccess(true);

		return response;
	}

    @RequestMapping(value="/goPremium", method=RequestMethod.POST)
    public @ResponseBody ApiResponseObject goPremium(HttpSession session, @RequestBody CreditCardInfo creditCardInfo){
        ApiResponseObject response = new ApiResponseObject();
        User user = (User) session.getAttribute("user");
        if (creditCardInfo.getIsValid()) {
            response.setSuccess(true);
            response.setResponseData(userService.makeUserPremium(user));
        } else {
            response.setSuccess(false);
            response.setMessage("Invalid credit card number.");
        }
        
        return response;
    }

}
