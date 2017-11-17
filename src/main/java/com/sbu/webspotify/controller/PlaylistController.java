package com.sbu.webspotify.controller;

import javax.servlet.http.HttpSession;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Playlist;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.repo.PlaylistRepository;
import com.sbu.webspotify.service.PlaylistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/playlist") // This means URL's start with /example (after Application path)
public class PlaylistController
{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getPlaylist(@PathVariable("id") int id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(playlist == null){
            response.setSuccess(false);
            response.setMessage("No playlist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(playlist);
        }
        return response;
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

    @RequestMapping(value = "/addSong", headers = "Accept=application/json")
    public @ResponseBody boolean addSongToPlaylist(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");        
        return playlistService.addSongToPlaylist(user, playlistId, songId);
    }	

    @RequestMapping(value = "/removeSong", headers = "Accept=application/json")
    public @ResponseBody boolean removeSongFromPlaylist(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");        
        return playlistService.removeSongFromPlaylist(user, playlistId, songId);
    }	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Playlist> getAllPlaylists()
    {
        return playlistService.getAllPlaylists();
    }

}