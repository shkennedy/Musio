package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;
import com.sbu.webspotify.service.SongService;

@Controller
@RequestMapping(path = "/song")
public class SongController
{
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getSong(@PathVariable("id") int id) {
        Song song = songService.getSongById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(song != null){
            response.setSuccess(true);
            response.setResponseData(song);
        }
        else {
            response.setSuccess(false);
            response.setMessage("No song found with ID "+id+".");
        }
        return response;
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject addSong(@RequestBody Song song) {
        Song newSong = songRepository.save(song);
        ApiResponseObject response = new ApiResponseObject();
        if (newSong != null) {
            response.setSuccess(true);
            response.setResponseData(newSong);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to create new song");
        }
        return response;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject updateSong(@RequestBody Song song) {
        Song updatedSong = songRepository.save(song);
        ApiResponseObject response = new ApiResponseObject();
        if (updatedSong != null) {
            response.setSuccess(true);
            response.setResponseData(updatedSong);
        } else {
            response.setSuccess(false);
            response.setMessage("Unable to updated song");
        }
        return response;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject deleteSong(@PathVariable("id") int id) {
        songRepository.delete(id);
        ApiResponseObject response = new ApiResponseObject();
        response.setSuccess(true);
        return response;
    }	
    
    @RequestMapping(value = "/getBasicAlbumInfo/{songId}")
	public @ResponseBody ApiResponseObject getAlbumInfo(@PathVariable("songId") int songId) {
        ApiResponseObject response = new ApiResponseObject();
        boolean songExists = songRepository.exists(songId);
        if(songExists == false) {
            response.setSuccess(false);
            response.setMessage("No song with id "+songId+" found.");
            return response;
        }

        response.setResponseData(albumRepository.getAlbumInfoForSong(songId));
        response.setSuccess(true);
        return response;
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Song> getAllSongs()
    {
        return songRepository.findAll();
    }

}