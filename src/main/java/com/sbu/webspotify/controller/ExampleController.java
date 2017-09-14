package com.sbu.webspotify.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/example") // URL's start with /example (after Application path)
public class ExampleController
{

    // specify the endpoint
    @GetMapping(path = "/{var}")
    public String example(@PathVariable String var, Model model)
    {
        model.addAttribute("variable", var);

        // example refers to the template called 'example.html'
        // you can find this file in 'resources/templates/' to see how it works
        // spring-boot handles this automatically
        return "example";
    }


}