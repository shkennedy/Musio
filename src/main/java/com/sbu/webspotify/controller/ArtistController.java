package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.repo.*;

@Controller
@RequestMapping(path = "/artist") // This means URL's start with /example (after Application path)
public class ArtistController
{
    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Artist getArtist(@PathVariable("id") int id) {
        return artistRepository.findById(id);
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Artist addArtist(@RequestBody Artist artist) {
		return artistRepository.save(artist);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Artist updateArtist(@RequestBody Artist artist) {
		return artistRepository.save(artist);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody void deleteArtist(@PathVariable("id") int id) {
        artistRepository.delete(id);
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Artist> getAllArtists()
    {
        return artistRepository.findAll();
    }

}