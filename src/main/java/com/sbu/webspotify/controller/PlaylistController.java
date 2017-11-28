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
	public @ResponseBody ApiResponseObject addPlaylist(@RequestBody Playlist playlist, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Playlist newPlaylist = playlistService.createPlaylist(playlist, user);
        ApiResponseObject response = new ApiResponseObject();
        if (newPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(newPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to create new playlist.");
        }
        return response;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject updatePlaylist(@RequestBody Playlist playlist) {
		Playlist updatedPlaylist = playlistRepository.save(playlist);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(updatedPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to update playlist.");
        }
        return response;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject deletePlaylist(@PathVariable("id") int id) {
        // Need check for ownership of playlist
        playlistRepository.delete(id);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(true);
        return response;
    }	

    @RequestMapping(value = "/addSong", headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject addSongToPlaylist(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");        
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(user, playlistId, songId);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(updatedPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to add song to playlist.");
        }
        return response;
    }	

    @RequestMapping(value = "/removeSong", headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject removeSongFromPlaylist(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId,
                                                HttpSession session) {
        User user = (User) session.getAttribute("user");        
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(user, playlistId, songId);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedPlaylist != null) {
            response.setSuccess(true);
            response.setResponseData(updatedPlaylist);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to remove song from playlist.");
        }
        return response;
    }	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Playlist> getAllPlaylists()
    {
        return playlistService.getAllPlaylists();
    }

}