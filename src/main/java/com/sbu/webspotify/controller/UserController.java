package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.model.*;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbu.webspotify.service.SongService;
import com.sbu.webspotify.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
	private UserService userService;

	@Autowired
	private SongService songService;

	@RequestMapping(value={"/getUsername"}, method = RequestMethod.GET)
	public @ResponseBody ApiResponseObject getMyUsername(HttpSession session){
		User user = (User) session.getAttribute("user");
		ApiResponseObject response = new ApiResponseObject();
		response.setResponseData(user.getUsername());
		response.setSuccess(true);
		return response;
	}

	@GetMapping(path = "/get/{var}")
	public @ResponseBody User getAUser(@PathVariable String var)
	{
		return userService.findUserByUsername(var);
	}

	@GetMapping(path="/whoami")
	public @ResponseBody User whoAmI(HttpSession session) {
		return (User) session.getAttribute("user");
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

	@RequestMapping(value={"/favorites/add/album/{albumId}"})
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

	@RequestMapping(value={"/getListeningHistory"})
	public @ResponseBody ApiResponseObject addSongToHistory(HttpSession session) {
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

}
