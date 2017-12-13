package com.sbu.webspotify.controller;

import java.util.Set;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.model.Concert;
import com.sbu.webspotify.repo.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/concert")
public class ConcertController
{
    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = "/getByArtist/{artistId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject getConcertsByArtist(@PathVariable("artistId") int artistId) {
        ApiResponseObject response = new ApiResponseObject();

        boolean artistExists = artistRepository.exists(artistId);
        if(artistExists == false) {
            response.setSuccess(false);
            response.setMessage("No artist found with id "+artistId+".");
            return response;
        }

        Set<Concert> concerts = artistRepository.findConcertsForArtist(artistId);
        response.setResponseData(concerts);
        response.setSuccess(true);

        return response;
    }

}