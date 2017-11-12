package com.sbu.webspotify.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sbu.webspotify.model.*;
import com.sbu.webspotify.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView checkLoginStatus(){

		// -- if user is already logged in redirect them to the app
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("forward:/");
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			bindingResult
					.rejectValue("username", "error.user",
							"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("registration");	
		}
		return modelAndView;
	}

}
