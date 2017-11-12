package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/song")
public class SongController
{
    @Autowired
    private SongRepository songRepository;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Song getSong(@PathVariable("id") int id) {
        return songRepository.findById(id);
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Song addSong(@RequestBody Song song) {
		return songRepository.save(song);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Song updateSong(@RequestBody Song song) {
		return songRepository.save(song);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody void deleteSong(@PathVariable("id") int id) {
        songRepository.delete(id);
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Song> getAllSongs()
    {
        return songRepository.findAll();
    }

}