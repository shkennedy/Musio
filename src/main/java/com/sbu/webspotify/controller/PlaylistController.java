package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;
import com.sbu.webspotify.service.PlaylistService;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/playlist") // This means URL's start with /example (after Application path)
public class PlaylistController
{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Playlist getPlaylist(@PathVariable("id") int id) {
        return playlistRepository.findById(id);
    }

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Playlist addPlaylist(@RequestBody Playlist playlist, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return playlistService.createPlaylist(playlist, user);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Playlist updatePlaylist(@RequestBody Playlist playlist) {
		return playlistRepository.save(playlist);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody void deletePlaylist(@PathVariable("id") int id) {
        playlistRepository.delete(id);
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Playlist> getAllPlaylists()
    {
        return playlistService.getAllPlaylists();
    }

}