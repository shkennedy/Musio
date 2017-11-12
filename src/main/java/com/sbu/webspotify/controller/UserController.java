package com.sbu.webspotify.controller;

import com.sbu.webspotify.model.*;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbu.webspotify.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

	@RequestMapping(value={"/getUsername"}, method = RequestMethod.GET)
	public @ResponseBody String getMyUsername(HttpSession session){
		User user = (User) session.getAttribute("user");
		return user.getUsername();
	}

	// TODO -- remove this
	// for testing (http://localhost:8080/user/get/test_admin) to see what it returns
	@GetMapping(path = "/get/{var}")
	public @ResponseBody User getAUser(@PathVariable String var)
	{
		return userService.findUserByUsername(var);
	}

	@RequestMapping(value={"/favorites/albums"}, method = RequestMethod.GET)
	public @ResponseBody Set<Album> getFavoriteAlbums(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteAlbums();
	}

	@RequestMapping(value={"/favorites/songs"}, method = RequestMethod.GET)
	public @ResponseBody Set<Song> getFavoriteSongs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteSongs();
	}

	@RequestMapping(value={"/favorites/playlists"}, method = RequestMethod.GET)
	public @ResponseBody Set<Playlist> getFavoritePlaylists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoritePlaylists();
	}

	@RequestMapping(value={"/favorites/artists"}, method = RequestMethod.GET)
	public @ResponseBody Set<Artist> getFavoriteArtists(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteArtists();
	}

	@RequestMapping(value={"/favorites/genres"}, method = RequestMethod.GET)
	public @ResponseBody Set<Genre> getFavoriteGenres(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteGenres();
	}

	@RequestMapping(value={"/favorites/stations"}, method = RequestMethod.GET)
	public @ResponseBody Set<Station> getFavoriteStations(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user.getFavoriteStations();
	}

}
