package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Artist;
import com.sbu.webspotify.repo.ArtistRepository;
import com.sbu.webspotify.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/artist") // This means URL's start with /example (after Application path)
public class ArtistController
{
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getArtist(@PathVariable("id") int id) {
        Artist artist = artistService.getArtistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(artist == null){
            response.setSuccess(false);
            response.setMessage("No artist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(artist);
        }
        return response;
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
    
    @RequestMapping(value = "/favoritesCount/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ApiResponseObject getFavoriteCount(@PathVariable("id") int id) {
        Artist artist = artistService.getArtistById(id);
        ApiResponseObject response = new ApiResponseObject();
        if(artist == null){
            response.setSuccess(false);
            response.setMessage("No artist found with ID "+id+".");
        }
        else {
            response.setSuccess(true);
            response.setResponseData(artistService.getFavoritesCountForArtist(id));
        }
        return response;
	}	

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Artist> getAllArtists()
    {
        return artistRepository.findAll();
    }

}