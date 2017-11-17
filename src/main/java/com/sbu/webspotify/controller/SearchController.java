package com.sbu.webspotify.controller;

import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController
{
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody SearchResults getSong(@RequestParam("query") String query) {
        return searchService.executeSearch(query);
    }

}