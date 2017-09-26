package com.wugao.jq.application.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
	
	@RequestMapping(value = "v/user/home", method = RequestMethod.GET)
	public ModelAndView toUserPage(String id) {
		return new ModelAndView("user/home");
	}

}
