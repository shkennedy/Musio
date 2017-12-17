package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.repo.InstrumentRepository;
import com.sbu.webspotify.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/instrument")
public class InstrumentController
{
    @Autowired
    private InstrumentRepository instrumentRepository;

    @Autowired
    private SearchService searchService;


    @RequestMapping(value = "/browse/{instrumentId}", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getBrowse(@PathVariable("instrumentId") int instrumentId) {
        ApiResponseObject response = new ApiResponseObject();

        boolean instrumentExists = instrumentRepository.exists(instrumentId);
        if(instrumentExists == false) {
            response.setSuccess(false);
            response.setMessage("No instrument found with id "+instrumentId+".");
            return response;
        }

        SearchResults results = searchService.searchByInstrument(instrumentId);
        response.setSuccess(true);
        response.setResponseData(results);
        return response;
    }

}