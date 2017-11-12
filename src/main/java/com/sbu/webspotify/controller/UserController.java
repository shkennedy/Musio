package com.sbu.webspotify.controller;

import com.sbu.webspotify.model.Album;
import com.sbu.webspotify.model.User;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbu.webspotify.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

	@RequestMapping(value={"/getUsername"}, method = RequestMethod.GET)
	public @ResponseBody String getMyUsername(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		return username;
	}

	// @RequestMapping(value={"/favorites/albums"}, method = RequestMethod.GET)
	// public @ResponseBody Set<Album> getFavoriteAlbums() {
	// 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// 	User user = userService.findUserByUsername(auth.getName());
	// 	return userService.getFavoriteAlbumsById(user.getId());
	// }

}
