package com.sbu.webspotify.controller;

import javax.servlet.http.HttpSession;

import com.sbu.webspotify.dto.ApiResponseObject;
import com.sbu.webspotify.dto.BrowseResults;
import com.sbu.webspotify.dto.SearchResults;
import com.sbu.webspotify.model.User;
import com.sbu.webspotify.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchAndBrowseController
{
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody ApiResponseObject doSearch(@RequestParam("query") String query) {
        SearchResults results = searchService.executeSearch(query);
        return new ApiResponseObject(true, null, results);
    }

    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    public @ResponseBody ApiResponseObject getBrowse(HttpSession session) {
		User user = (User) session.getAttribute("user");
        BrowseResults results = searchService.getBrowseContent(user);
        return new ApiResponseObject(true, null, results);
    }

}