package com.wugao.jq.application.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class IntroduceController {

	
	@RequestMapping(value = "v/introduce", method = RequestMethod.GET)
	public ModelAndView toIntroducePage() {
		return new ModelAndView("web/introduce");
	}
}
