package com.wugao.center.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginConttoller {

	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(String username, String password){
		return "authentication success";
	}
}
