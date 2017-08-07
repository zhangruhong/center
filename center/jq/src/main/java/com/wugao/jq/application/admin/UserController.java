package com.wugao.jq.application.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("user")
public class UserController {
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView toUserPage(String id) {
		return new ModelAndView("user/home");
	}

}
