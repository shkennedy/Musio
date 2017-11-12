package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/album")
public class AlbumController
{
    @Autowired
    private AlbumRepository albumRepository;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Album getAlbum(@PathVariable("id") int id) {
        return albumRepository.findById(id);
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Album addAlbum(@RequestBody Album album) {
		return albumRepository.save(album);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Album updateAlbum(@RequestBody Album album) {
		return albumRepository.save(album);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody void deleteAlbum(@PathVariable("id") int id) {
        albumRepository.delete(id);
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Album> getAllAlbums()
    {
        return albumRepository.findAll();
    }

}