package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.repo.GenreRepository;
import com.sbu.webspotify.service.GenreService;
import com.sbu.webspotify.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping(path = "/genre")
public class GenreController
{
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private SearchService searchService;

    
    @RequestMapping(value = "/browse/{genreId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getBrowse(@PathVariable("genreId") int genreId) {
        ApiResponseObject response = new ApiResponseObject();

        boolean genreExists = genreRepository.exists(genreId);
        if(genreExists == false) {
            response.setSuccess(false);
            response.setMessage("No genre found with id "+genreId+".");
            return response;
        }

        SearchResults results = searchService.searchByGenre(genreId);
        response.setSuccess(true);
        response.setResponseData(results);
        return response;
    }

    @RequestMapping(value = "/songs/{genreId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getSongsByBrowse(@PathVariable("genreId") int genreId) {
        ApiResponseObject response = new ApiResponseObject();

        boolean genreExists = genreRepository.exists(genreId);
        if(genreExists == false) {
            response.setSuccess(false);
            response.setMessage("No genre found with id "+genreId+".");
            return response;
        }

        Set<SongIdentifier> results = genreService.getSongsByGenreId(genreId);
        response.setSuccess(true);
        response.setResponseData(results);
        return response;
    }
}